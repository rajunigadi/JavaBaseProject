package com.raju.javabaseproject.data.source.remote.http

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        // re-write response header to force use of cache
        val cacheControl = CacheControl.Builder()
                .maxAge(0, TimeUnit.MINUTES)
                .build()
        return response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
    }

    companion object {
        private val CACHE_CONTROL = "Cache-Control"
    }
}
