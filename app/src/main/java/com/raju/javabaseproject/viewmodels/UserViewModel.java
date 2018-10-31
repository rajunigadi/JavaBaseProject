package com.raju.javabaseproject.viewmodels;

import android.arch.lifecycle.MutableLiveData;

import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.data.source.UserRepository;
import com.raju.javabaseproject.viewmodels.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends BaseViewModel {

    private UserRepository repository;
    public MutableLiveData<List<User>> userResult = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    UserViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void getUsers() {
        compositeDisposable.add(repository.getUsers()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
                userResult.postValue(users);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                error.postValue(throwable.getMessage());
            }
        }));
    }
}
