package com.raju.javabaseproject.dagger.modules.base;

import com.raju.javabaseproject.BuildConfig;
import com.raju.javabaseproject.data.utils.SharedPrefsHelper;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

@Module
public class HttpModule {

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    @Provides
    Interceptor provideNetworkInterceptor() {
        return new NoOpInterceptor();
    }

    @Provides
    @Named("LOGGING")
    Interceptor provideLoggingInterceptor() {
        //For Debug
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
        return httpLoggingInterceptor;
    }

    private static class NoOpInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    }
}
