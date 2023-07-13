package com.lbd.testliquibase.exceptions;



public class MyException2 extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int statusCode;


    public MyException2(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
