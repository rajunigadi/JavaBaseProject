package com.raju.javabaseproject.data.source.remote.api

import com.raju.javabaseproject.data.model.User

import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {
    @get:GET("users")
    val users: Observable<MutableList<User>>
}
