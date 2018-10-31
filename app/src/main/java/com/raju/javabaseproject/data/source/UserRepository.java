package com.raju.javabaseproject.data.source;

import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.data.source.local.dao.UserDao;
import com.raju.javabaseproject.data.source.remote.api.UserApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class UserRepository {

    private UserApi api;
    private UserDao dao;

    @Inject
    UserRepository(UserApi api, UserDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public Observable<List<User>> getUsers() {
        return Observable.concatArrayEager(getFromDb(), getFromNetwork());
        /*return getFromNetwork();*/
    }

    private Observable<List<User>> getFromNetwork() {
        return api.getUsers()
                .doOnNext(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        dao.add(users);
                    }
                });
    }

    private Observable<List<User>> getFromDb() {
        return dao.getUsersAsFlowable().toObservable();
    }
}
