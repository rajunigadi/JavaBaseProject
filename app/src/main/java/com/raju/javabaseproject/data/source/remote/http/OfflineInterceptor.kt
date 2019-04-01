package com.raju.javabaseproject.data.source.remote.http

import android.content.Context

import com.raju.javabaseproject.data.utils.NetworkUtil

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class OfflineInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtil.isConnected(context)) {
            val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
        }

        return chain.proceed(request)
    }
}

