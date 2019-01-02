package com.raju.javabaseproject.mvp.model;

/**
 * Created by Rajashekhar Vanahalli on 06/07/18.
 */
public class ErrorResponse {

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
