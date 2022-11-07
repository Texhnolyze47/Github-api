package com.texhnolyze.githubapiv2;

public class ServiceException extends  RuntimeException {

    private int statusCode;

    public ServiceException (String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ServiceException (String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
