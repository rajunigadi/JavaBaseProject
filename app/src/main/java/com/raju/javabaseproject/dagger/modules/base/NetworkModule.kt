package com.raju.javabaseproject.dagger.modules.base

import android.content.Context

import com.raju.javabaseproject.R
import com.raju.javabaseproject.data.source.remote.http.HeaderInterceptor
import com.raju.javabaseproject.data.source.remote.http.HttpClientBuilder
import com.raju.javabaseproject.data.utils.SharedPrefsHelper
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [HttpModule::class])
class NetworkModule {

    @Provides
    @Singleton
    internal fun providesOkHttpClient(context: Context, networkInterceptor: Interceptor, @Named("LOGGING") loggingInterceptor: Interceptor,
                                      sharedPrefsHelper: SharedPrefsHelper): OkHttpClient {
        return buildOkHttpClient(context, context.getString(R.string.app_name), 50, networkInterceptor, loggingInterceptor,
                HeaderInterceptor(sharedPrefsHelper))
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(context: Context, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(context.resources.getString(R.string.endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit
    }

    private fun buildOkHttpClient(context: Context, cacheName: String, cacheSize: Int, networkInterceptor: Interceptor?,
                                  loggingInterceptor: Interceptor?, headerInterceptor: Interceptor?): OkHttpClient {
        val builder = HttpClientBuilder(context)
                .setTimeouts(120)
                .setCache(cacheName, cacheSize)
                .setLoggingInterceptor(loggingInterceptor!!)
                .setWireFormatHeaderInterceptor(headerInterceptor!!)
                .enableSocketLog(true)

        return builder.build()
    }
}
