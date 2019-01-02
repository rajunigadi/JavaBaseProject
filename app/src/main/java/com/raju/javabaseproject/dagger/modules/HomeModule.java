package com.raju.javabaseproject.dagger.modules;

import com.raju.javabaseproject.data.api.UserApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Rajashekhar Vanahalli on 09/04/18.
 */

@Module
public class HomeModule {
    @Provides
    UserApi providesHomeApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
