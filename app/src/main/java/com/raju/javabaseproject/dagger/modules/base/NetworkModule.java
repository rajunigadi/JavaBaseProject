package com.raju.javabaseproject.dagger.modules.base;

import android.content.Context;
import android.support.annotation.Nullable;

import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.http.HeaderInterceptor;
import com.raju.javabaseproject.data.http.HttpClientBuilder;
import com.raju.javabaseproject.data.utils.SharedPrefsHelper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

@Module(includes = HttpModule.class)
public class NetworkModule {

    public NetworkModule() {

    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Context context, Interceptor networkInterceptor, @Named("LOGGING") Interceptor loggingInterceptor,
                                      SharedPrefsHelper sharedPrefsHelper) {
        return buildOkHttpClient(context, context.getString(R.string.app_name), 50, networkInterceptor, loggingInterceptor,
                new HeaderInterceptor(sharedPrefsHelper));
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(Context context, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.getResources().getString(R.string.endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    private OkHttpClient buildOkHttpClient(Context context, String cacheName, int cacheSize, @Nullable Interceptor networkInterceptor,
                                           @Nullable Interceptor loggingInterceptor, @Nullable Interceptor headerInterceptor) {
        HttpClientBuilder builder =
                new HttpClientBuilder(context)
                        .setTimeouts(120)
                        .setCache(cacheName, cacheSize)
                        .setLoggingInterceptor(loggingInterceptor)
                        .setWireFormatHeaderInterceptor(headerInterceptor)
                        .enableSocketLog(true);

        return builder.build();
    }
}
