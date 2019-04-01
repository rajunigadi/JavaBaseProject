package com.raju.javabaseproject.data.source.remote.http

import android.content.Context

import com.raju.javabaseproject.data.utils.NetworkUtil

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ConnectivityInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isConnected(context)) {
            throw ConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

