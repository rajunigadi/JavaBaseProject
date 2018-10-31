package com.raju.javabaseproject.data.source.remote.utils;

import android.content.Context;

import com.raju.javabaseproject.data.source.remote.utils.interceptors.CacheInterceptor;
import com.raju.javabaseproject.data.source.remote.utils.interceptors.ConnectivityInterceptor;
import com.raju.javabaseproject.data.source.remote.utils.interceptors.HeaderInterceptor;
import com.raju.javabaseproject.data.source.remote.utils.interceptors.OfflineInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Connection;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import timber.log.Timber;

public class HttpClientBuilder {

    private int timeoutConnect;
    private int timeoutRead;
    private int timeoutWrite;

    private String cacheName;
    private int cacheSizeMB;
    private boolean enableSocketLog;

    private SSLSocketFactory unsafeSSLSocketFactory;
    private X509TrustManager x509TrustManager;
    private HostnameVerifier hostnameVerifier;

    private Interceptor networkInterceptor, loggingInterceptor, wireFormatHeaderInterceptor;

    private CookieJar cookieJar;

    private final Context context;

    public HttpClientBuilder(Context context) {
        this.context = context;
    }

    public OkHttpClient build() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();

        okClientBuilder.addInterceptor(new ConnectivityInterceptor(context));
        if (null != loggingInterceptor) {
            okClientBuilder.addInterceptor(loggingInterceptor);
        }

        final File baseDir = context.getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, cacheName);
            int cacheSize = cacheSizeMB * 1024 * 1024;
            okClientBuilder.cache(new Cache(cacheDir, cacheSize));
        }

        okClientBuilder.connectTimeout(this.timeoutConnect, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(this.timeoutRead, TimeUnit.SECONDS);
        okClientBuilder.writeTimeout(this.timeoutWrite, TimeUnit.SECONDS);

        if (unsafeSSLSocketFactory != null) {
            okClientBuilder.sslSocketFactory(unsafeSSLSocketFactory, x509TrustManager);
            okClientBuilder.hostnameVerifier(hostnameVerifier);
        }

        if (null != networkInterceptor) {
            okClientBuilder.addNetworkInterceptor(networkInterceptor);
        }
        if (null != wireFormatHeaderInterceptor) {
            okClientBuilder.addInterceptor(wireFormatHeaderInterceptor);
        }

        if (null != cookieJar) {
            okClientBuilder.cookieJar(cookieJar);
        }

        if (enableSocketLog) {
            okClientBuilder.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Connection conn = chain.connection();
                    return chain.proceed(chain.request());
                }
            });
        }

        okClientBuilder.addInterceptor(new HeaderInterceptor());
        okClientBuilder.addInterceptor(new OfflineInterceptor(context));
        okClientBuilder.addNetworkInterceptor(new CacheInterceptor());
        okClientBuilder.cache(provideCache());
        return okClientBuilder.build();
    }

    public HttpClientBuilder setCache(String cacheName, int cacheSizeMB) {
        this.cacheName = cacheName;
        this.cacheSizeMB = cacheSizeMB;
        return this;
    }

    public HttpClientBuilder setTimeouts(int timeout) {
        return setConnectTimeout(timeout)
                .setReadTimeout(timeout)
                .setWriteTimeout(timeout);
    }

    public HttpClientBuilder setConnectTimeout(int timeout) {
        this.timeoutConnect = timeout;
        return this;
    }

    public HttpClientBuilder setReadTimeout(int timeout) {
        this.timeoutRead = timeout;
        return this;
    }

    public HttpClientBuilder setWriteTimeout(int timeout) {
        this.timeoutWrite = timeout;
        return this;
    }

    public HttpClientBuilder setNetworkInterceptor(Interceptor interceptor) {
        this.networkInterceptor = interceptor;
        return this;
    }

    public HttpClientBuilder setWireFormatHeaderInterceptor(Interceptor wireFormatHeaderInterceptor) {
        this.wireFormatHeaderInterceptor = wireFormatHeaderInterceptor;
        return this;
    }

    public HttpClientBuilder setLoggingInterceptor(Interceptor interceptor) {
        this.loggingInterceptor = interceptor;
        return this;
    }

    public HttpClientBuilder setCookieJar(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
        return this;
    }

    public HttpClientBuilder enableSocketLog(boolean enable) {
        this.enableSocketLog = enable;
        return this;
    }

    public HttpClientBuilder setUnsafeSSLSocketFactory(SSLSocketFactory factory,
                                                       X509TrustManager trustManager,
                                                       HostnameVerifier hostnameVerifier) {
        this.unsafeSSLSocketFactory = factory;
        this.x509TrustManager = trustManager;
        this.hostnameVerifier = hostnameVerifier;
        return this;
    }

    private Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(context.getCacheDir(), "http-cache"),
                    10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Timber.d("Could not create Cache!");
        }
        return cache;
    }
}
