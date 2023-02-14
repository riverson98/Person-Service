package com.attornatus.test.personservice.exceptions;

public abstract class BaseCustomException extends RuntimeException implements CustomException{

    protected BaseCustomException(String message) {
        super(message);
    }

    @Override
    public Integer getStatusCode() {
        return null;
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
