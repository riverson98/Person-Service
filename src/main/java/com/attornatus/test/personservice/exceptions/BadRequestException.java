package com.attornatus.test.personservice.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseCustomException{

    public static final String ERROR_CODE = "INVALID_REQUEST";
    public static final String DEFAULT_MESSAGE = "Requisição inválida";
    public static final Integer HTTP_STATUS_CODE = HttpStatus.BAD_REQUEST.value();

    public BadRequestException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public Integer getStatusCode() {
        return HTTP_STATUS_CODE;
    }

    @Override
    public String getErrorCode() {
        return ERROR_CODE;
    }
}
