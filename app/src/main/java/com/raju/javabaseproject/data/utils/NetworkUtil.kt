package com.raju.javabaseproject.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkUtil @Inject
internal constructor(context: Context) {

    val isConnected: Boolean
        get() = connectivity.isConnected

    private val connectivity: Connectivity
    var isConnectionFast = false
        private set

    init {
        this.connectivity = Connectivity(context)
        isConnectionFast = connectivity.isConnectedFast
    }

    fun update(): Boolean {
        isConnectionFast = connectivity.isConnectedFast
        return isConnectionFast
    }

    companion object {

        fun isConnected(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }
    }
}

