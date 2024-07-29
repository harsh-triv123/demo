package com.user.dto;

import java.util.Date;

public class ErrorDetails {
    //consist of details of exception object
    private String errorMessage;
    private Date date;
    private String webRequest;

    public ErrorDetails(String errorMessage, Date date, String webRequest) {
        this.errorMessage = errorMessage;
        this.date = date;
        this.webRequest = webRequest;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Date getDate() {
        return date;
    }

    public String getWebRequest() {
        return webRequest;
    }
}
