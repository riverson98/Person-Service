package com.attornatus.test.personservice.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BaseCustomException{

    public static final String ERROR_CODE = "SYSTEM_INTERNAL_SERVER_ERROR";
    public static final String DEFAULT_MESSAGE = "Sistema Indisponivel";
    public static final Integer HTTP_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public InternalServerErrorException() {
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
