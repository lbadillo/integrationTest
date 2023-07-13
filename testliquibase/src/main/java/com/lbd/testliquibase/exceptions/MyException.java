package com.lbd.testliquibase.exceptions;



public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int statusCode;


    public MyException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
