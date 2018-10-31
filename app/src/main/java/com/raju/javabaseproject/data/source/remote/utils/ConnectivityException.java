package com.raju.javabaseproject.data.source.remote.utils;

import java.io.IOException;

public class ConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No Internet Connection";
    }
}
