package com.raju.javabaseproject.data.source.remote.utils.interceptors;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {

    public CacheInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        // re-write response header to force use of cache
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(10, TimeUnit.MINUTES)
                .build();
        return response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build();
    }

    private static final String CACHE_CONTROL = "Cache-Control";
}

