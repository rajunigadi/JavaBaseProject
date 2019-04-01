package com.raju.javabaseproject.dagger.modules.base

import com.raju.javabaseproject.BuildConfig
import com.raju.javabaseproject.data.utils.SharedPrefsHelper

import java.io.IOException

import javax.inject.Inject
import javax.inject.Named

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

import okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

@Module
class HttpModule {

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    @Provides
    internal fun provideNetworkInterceptor(): Interceptor {
        return NoOpInterceptor()
    }

    @Provides
    @Named("LOGGING")
    internal fun provideLoggingInterceptor(): Interceptor {
        //For Debug
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HEADERS else NONE
        return httpLoggingInterceptor
    }

    private class NoOpInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}
