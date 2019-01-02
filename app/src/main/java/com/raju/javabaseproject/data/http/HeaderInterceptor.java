package com.raju.javabaseproject.data.http;

import android.text.TextUtils;

import com.raju.javabaseproject.data.utils.SharedPrefsHelper;
import com.raju.javabaseproject.utlities.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class HeaderInterceptor implements Interceptor {

    private SharedPrefsHelper sharedPrefsHelper;

    public HeaderInterceptor(SharedPrefsHelper sharedPrefsHelper) {
        this.sharedPrefsHelper = sharedPrefsHelper;
    }

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
                .build();

        String authCode = sharedPrefsHelper.get(Constants.PrefsKeys.KEY_AUTH_CODE, "");
        if(!TextUtils.isEmpty(authCode)) {
            request = requestBuilder
                    .addHeader("Authorization", authCode)
                    .build();
        }

        return chain.proceed(request);
    }
}

