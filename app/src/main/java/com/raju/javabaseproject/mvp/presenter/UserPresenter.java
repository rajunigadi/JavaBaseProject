package com.raju.javabaseproject.mvp.presenter;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.raju.javabaseproject.data.api.UserApi;
import com.raju.javabaseproject.mvp.model.ErrorResponse;
import com.raju.javabaseproject.mvp.model.ServerResponse;
import com.raju.javabaseproject.mvp.presenter.base.BasePresenter;
import com.raju.javabaseproject.mvp.view.IUsersView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class UserPresenter extends BasePresenter<IUsersView> {

    @Inject
    Gson gson;

    @Inject
    UserPresenter(UserApi usersApi) {
        this.usersApi = usersApi;
    }

    public void getUsers() {
        if(getView() != null) {
            getView().showLoading();
        }
        disposable.add(usersApi.getUsers()
                .map(new Function<ServerResponse, ServerResponse>() {
                    @Override
                    public ServerResponse apply(ServerResponse serverResponse) throws Exception {
                        return serverResponse;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ServerResponse>() {
                    @Override
                    public void accept(ServerResponse serverResponse) throws Exception {
                        getView().bindData(serverResponse);
                        getView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String errorMessage = null;
                        try {
                            if (throwable instanceof HttpException) {
                                ResponseBody body = ((HttpException) throwable).response().errorBody();
                                ErrorResponse errorResponse = gson.fromJson(body.string(), ErrorResponse.class);
                                if (errorResponse != null) {
                                    errorMessage = errorResponse.getMessage();
                                }
                            }
                        } catch (Exception e) {
                            // errorMessage = e.getMessage();
                        }
                        getView().showError(errorMessage);
                        getView().hideLoading();
                    }
                }));
    }

    private UserApi usersApi;
}
