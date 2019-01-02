package com.raju.javabaseproject.data.source;

import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.data.source.remote.api.UserApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRepository {

    private UserApi api;

    @Inject
    UserRepository(UserApi api) {
        this.api = api;
    }

    public Observable<List<User>> getUsers() {
        return api.getUsers();
    }
}
