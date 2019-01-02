package com.raju.javabaseproject.mvp.model;

/**
 * Created by Rajashekhar Vanahalli on 12/6/17.
 */

public class HeaderItem implements ListItem {

    private String header;
    private double header2;

    public HeaderItem(String header) {
        this.header = header;
    }

    public HeaderItem(String header, double header2) {
        this.header = header;
        this.header2 = header2;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public double getHeader2() {
        return header2;
    }

    public void setHeader2(double header2) {
        this.header2 = header2;
    }
}
