package com.raju.javabaseproject.di.modules;

import com.raju.javabaseproject.data.source.remote.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UserModule {
    @Provides
    UserApi providesUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
