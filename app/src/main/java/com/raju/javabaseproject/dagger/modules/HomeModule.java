package com.raju.javabaseproject.dagger.modules;

import com.raju.javabaseproject.data.source.remote.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HomeModule {
    @Provides
    UserApi providesHomeApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
