package com.raju.javabaseproject.data.source.remote.api;

import com.raju.javabaseproject.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserApi {
    @GET("users")
    Observable<List<User>> getUsers();
}
