package com.attornatus.test.personservice.exceptions.handler;


import com.attornatus.test.personservice.exceptions.BadRequestException;
import com.attornatus.test.personservice.exceptions.BaseCustomException;
import com.attornatus.test.personservice.exceptions.InternalServerErrorException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomResponseExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BaseCustomException.class})
    public ResponseEntity<ErrorResponse> customExceptionHandler(BaseCustomException customException){
        log.info("customHandler {}" , customException.getMessage());
        var errorResponse = new ErrorResponse(customException.getErrorCode(),customException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(customException.getStatusCode()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception){
        log.info("genericHandler {}" , exception.getMessage());
        var errorResponse = new ErrorResponse(InternalServerErrorException.ERROR_CODE
                ,InternalServerErrorException.DEFAULT_MESSAGE);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException exception){
        log.error("handlerConstraintViolationException {}", exception.getMessage());
        var errorResponse = new ErrorResponse(BadRequestException.ERROR_CODE,
                BadRequestException.DEFAULT_MESSAGE);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        log.info("handleMethodArgumentNotValid {}", ex.getMessage());
        var errorResponse = new ErrorResponse(BadRequestException.ERROR_CODE,
                BadRequestException.DEFAULT_MESSAGE);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
