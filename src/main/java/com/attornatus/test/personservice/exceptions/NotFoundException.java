package com.attornatus.test.personservice.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseCustomException{

    public static final String ERROR_CODE = "PERSON_NOT_FOUND";
    public static final String DEFAULT_MESSAGE = "Desculpe! não encontramos registros referente a sua busca em nossa base.";
    public static final Integer HTTP_STATUS_CODE = HttpStatus.NOT_FOUND.value();

    public NotFoundException() {
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
