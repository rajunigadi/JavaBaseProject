package com.raju.javabaseproject.data.model;

public class HeaderItem implements ListItem {

    private String header;
    private String subHeader;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubHeader() {
        return subHeader;
    }

    public void setSubHeader(String subHeader) {
        this.subHeader = subHeader;
    }
}
