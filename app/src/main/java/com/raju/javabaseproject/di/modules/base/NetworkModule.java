package com.raju.javabaseproject.di.modules.base;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.raju.javabaseproject.BuildConfig;
import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.source.remote.utils.HttpClientBuilder;
import com.raju.javabaseproject.data.source.remote.utils.interceptors.HeaderInterceptor;
import com.raju.javabaseproject.utilities.Constants;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    Interceptor provideNetworkInterceptor() {
        return new NoOpInterceptor();
    }

    @Provides
    @Named("LOGGING")
    Interceptor provideLoggingInterceptor() {
        //For Debug
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Timber.d(message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient(Context context, @Named("LOGGING") Interceptor loggingInterceptor) {
        return new HttpClientBuilder(context)
                .setTimeouts(120)
                .setCache(context.getString(R.string.app_name), 50)
                .setLoggingInterceptor(loggingInterceptor)
                .setWireFormatHeaderInterceptor(new HeaderInterceptor())
                .enableSocketLog(true).build();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(Context context, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class NoOpInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    }
}
