package com.raju.javabaseproject.data.http;

import java.io.IOException;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class ConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No Internet Connection";
    }

}

