package com.raju.javabaseproject.data.source.remote.utils.interceptors;

import java.io.IOException;
import java.util.TimeZone;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        /*HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("timeZone", TimeZone.getDefault().getDisplayName())
                .build();*/

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder();
        Request request = requestBuilder
                .addHeader("timeZone", TimeZone.getDefault().getDisplayName())
                .build();

        return chain.proceed(request);
    }
}

