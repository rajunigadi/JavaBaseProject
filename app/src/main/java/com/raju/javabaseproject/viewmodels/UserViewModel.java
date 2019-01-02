package com.raju.javabaseproject.viewmodels;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.raju.javabaseproject.data.model.ErrorResponse;
import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.data.source.UserRepository;
import com.raju.javabaseproject.viewmodels.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class UserViewModel extends BaseViewModel {

    private UserRepository repository;
    private Gson gson;

    public MutableLiveData<List<User>> result = new MutableLiveData();
    public MutableLiveData<String> error = new MutableLiveData();

    @Inject
    UserViewModel(UserRepository repository, Gson gson) {
        this.repository = repository;
        this.gson = gson;
    }

    public void getUsers() {
        compositeDisposable.add(repository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        result.postValue(users);
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
                        error.postValue(errorMessage);
                    }
                }));
    }
}
