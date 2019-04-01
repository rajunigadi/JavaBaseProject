package com.raju.javabaseproject.data.source.remote.http

import android.content.Context

import java.io.File
import java.util.concurrent.TimeUnit

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import timber.log.Timber

class HttpClientBuilder(private val context: Context) {

    private var timeoutConnect: Int = 0
    private var timeoutRead: Int = 0
    private var timeoutWrite: Int = 0

    private var cacheName: String? = null
    private var cacheSizeMB: Int = 0
    private var enableSocketLog: Boolean = false

    private var unsafeSSLSocketFactory: SSLSocketFactory? = null
    private var x509TrustManager: X509TrustManager? = null
    private var hostnameVerifier: HostnameVerifier? = null

    private var networkInterceptor: Interceptor? = null
    private var loggingInterceptor: Interceptor? = null
    private var wireFormatHeaderInterceptor: Interceptor? = null

    private var cookieJar: CookieJar? = null

    fun build(): OkHttpClient {
        val okClientBuilder = OkHttpClient.Builder()

        okClientBuilder.addInterceptor(ConnectivityInterceptor(context))
        if (null != loggingInterceptor) {
            okClientBuilder.addInterceptor(loggingInterceptor!!)
        }

        val baseDir = context.cacheDir
        if (baseDir != null) {
            val cacheDir = File(baseDir, cacheName!!)
            val cacheSize = cacheSizeMB * 1024 * 1024
            okClientBuilder.cache(Cache(cacheDir, cacheSize.toLong()))
        }

        okClientBuilder.connectTimeout(this.timeoutConnect.toLong(), TimeUnit.SECONDS)
        okClientBuilder.readTimeout(this.timeoutRead.toLong(), TimeUnit.SECONDS)
        okClientBuilder.writeTimeout(this.timeoutWrite.toLong(), TimeUnit.SECONDS)

        if (unsafeSSLSocketFactory != null) {
            okClientBuilder.sslSocketFactory(unsafeSSLSocketFactory!!, x509TrustManager!!)
            okClientBuilder.hostnameVerifier(hostnameVerifier!!)
        }

        if (null != networkInterceptor) {
            okClientBuilder.addNetworkInterceptor(networkInterceptor!!)
        }
        if (null != wireFormatHeaderInterceptor) {
            okClientBuilder.addInterceptor(wireFormatHeaderInterceptor!!)
        }

        if (null != cookieJar) {
            okClientBuilder.cookieJar(cookieJar!!)
        }

        /*if (enableSocketLog) {
            okClientBuilder.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Connection conn = chain.connection();
                    return chain.proceed(chain.request());
                }
            });
        }*/

        okClientBuilder.addInterceptor(OfflineInterceptor(context))
        //okClientBuilder.addNetworkInterceptor(new CacheInterceptor());
        okClientBuilder.cache(provideCache())
        return okClientBuilder.build()
    }

    fun setCache(cacheName: String, cacheSizeMB: Int): HttpClientBuilder {
        this.cacheName = cacheName
        this.cacheSizeMB = cacheSizeMB
        return this
    }

    fun setTimeouts(timeout: Int): HttpClientBuilder {
        return setConnectTimeout(timeout)
                .setReadTimeout(timeout)
                .setWriteTimeout(timeout)
    }

    fun setConnectTimeout(timeout: Int): HttpClientBuilder {
        this.timeoutConnect = timeout
        return this
    }

    fun setReadTimeout(timeout: Int): HttpClientBuilder {
        this.timeoutRead = timeout
        return this
    }

    fun setWriteTimeout(timeout: Int): HttpClientBuilder {
        this.timeoutWrite = timeout
        return this
    }

    fun setNetworkInterceptor(interceptor: Interceptor): HttpClientBuilder {
        this.networkInterceptor = interceptor
        return this
    }

    fun setWireFormatHeaderInterceptor(wireFormatHeaderInterceptor: Interceptor): HttpClientBuilder {
        this.wireFormatHeaderInterceptor = wireFormatHeaderInterceptor
        return this
    }

    fun setLoggingInterceptor(interceptor: Interceptor): HttpClientBuilder {
        this.loggingInterceptor = interceptor
        return this
    }

    fun setCookieJar(cookieJar: CookieJar): HttpClientBuilder {
        this.cookieJar = cookieJar
        return this
    }

    fun enableSocketLog(enable: Boolean): HttpClientBuilder {
        this.enableSocketLog = enable
        return this
    }

    fun setUnsafeSSLSocketFactory(factory: SSLSocketFactory,
                                  trustManager: X509TrustManager,
                                  hostnameVerifier: HostnameVerifier): HttpClientBuilder {
        this.unsafeSSLSocketFactory = factory
        this.x509TrustManager = trustManager
        this.hostnameVerifier = hostnameVerifier
        return this
    }

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http-cache"),
                    (10 * 1024 * 1024).toLong()) // 10 MB
        } catch (e: Exception) {
            Timber.d("Could not create Cache!")
        }

        return cache
    }
}
