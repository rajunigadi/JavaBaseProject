package com.raju.javabaseproject.dagger.modules

import com.raju.javabaseproject.data.source.remote.api.UserApi

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeModule {
    @Provides
    internal fun providesApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
