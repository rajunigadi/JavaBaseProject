package com.raju.javabaseproject.data.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class CacheInterceptor  implements Interceptor {

    public CacheInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        // re-write response header to force use of cache
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(0, TimeUnit.MINUTES)
                .build();
        return response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build();
    }

    private static final String CACHE_CONTROL = "Cache-Control";
}
