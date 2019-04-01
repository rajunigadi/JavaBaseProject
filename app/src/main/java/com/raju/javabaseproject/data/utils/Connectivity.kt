package com.raju.javabaseproject.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager


class Connectivity internal constructor(private val context: Context) {

    private val networkInfo: NetworkInfo?
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo
        }

    val isConnected: Boolean
        get() {
            val info = networkInfo
            return info != null && info.isConnected
        }

    val isConnectedWifi: Boolean
        get() {
            val info = networkInfo
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
        }

    val isConnectedMobile: Boolean
        get() {
            val info = networkInfo
            return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
        }

    val isConnectedFast: Boolean
        get() {
            val info = networkInfo
            return info != null && info.isConnected && isConnectionFast(info.type, info.subtype)
        }

    fun isConnectionFast(type: Int, subType: Int): Boolean {
        return if (type == ConnectivityManager.TYPE_WIFI) {
            true
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
                TelephonyManager.NETWORK_TYPE_EDGE -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600-1400 kbps
                TelephonyManager.NETWORK_TYPE_GPRS -> false // ~ 100 kbps
                TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2-14 Mbps
                TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700-1700 kbps
                TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1-23 Mbps
                TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400-7000 kbps
                /*
                 * Above API level 7, make sure to set android:targetSdkVersion
                 * to appropriate level to use these
                 */
                TelephonyManager.NETWORK_TYPE_EHRPD // API level 11
                -> true // ~ 1-2 Mbps
                TelephonyManager.NETWORK_TYPE_EVDO_B // API level 9
                -> true // ~ 5 Mbps
                TelephonyManager.NETWORK_TYPE_HSPAP // API level 13
                -> true // ~ 10-20 Mbps
                TelephonyManager.NETWORK_TYPE_IDEN // API level 8
                -> false // ~25 kbps
                TelephonyManager.NETWORK_TYPE_LTE // API level 11
                -> true // ~ 10+ Mbps
                // Unknown
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                else -> false
            }
        } else {
            false
        }
    }
}
