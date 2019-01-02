package com.raju.javabaseproject.data.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

@Singleton
public class NetworkUtil {

    @Inject
    NetworkUtil(Context context) {
        this.connectivity = new Connectivity(context);
        connectionFast = connectivity.isConnectedFast();
    }

    public boolean update() {
        connectionFast = connectivity.isConnectedFast();
        return isConnectionFast();
    }

    public boolean isConnectionFast() {
        return connectionFast;
    }

    public boolean isConnected() {
        return connectivity.isConnected();
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    private final Connectivity connectivity;
    private boolean connectionFast = false;
}

