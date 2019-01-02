package com.raju.javabaseproject.data.api;

import com.raju.javabaseproject.mvp.model.ServerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Rajashekhar Vanahalli on 09/04/18.
 */

public interface UserApi {
    @GET("mobile/user/overview")
    Observable<ServerResponse> getUsers();
}
