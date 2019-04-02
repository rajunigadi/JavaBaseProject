package com.raju.javabaseproject.data.source

import com.raju.javabaseproject.data.model.User
import com.raju.javabaseproject.data.source.remote.api.UserApi

import javax.inject.Inject

import io.reactivex.Observable

class UserRepository @Inject
    internal constructor(private val api: UserApi) {

    val users: Observable<MutableList<User>>
        get() = api.users
}
