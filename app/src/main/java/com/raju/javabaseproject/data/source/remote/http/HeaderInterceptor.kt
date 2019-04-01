package com.raju.javabaseproject.data.source.remote.http

import android.text.TextUtils

import com.raju.javabaseproject.data.utils.SharedPrefsHelper
import com.raju.javabaseproject.utlities.Constants

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class HeaderInterceptor(private val sharedPrefsHelper: SharedPrefsHelper) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        /*HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("timeZone", TimeZone.getDefault().getDisplayName())
                .build();*/

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
        var request = requestBuilder
                .build()

        val authCode = sharedPrefsHelper.get(Constants.PrefsKeys.KEY_AUTH_CODE, "")
        if (!TextUtils.isEmpty(authCode)) {
            request = requestBuilder
                    .addHeader("Authorization", authCode!!)
                    .build()
        }

        return chain.proceed(request)
    }
}

