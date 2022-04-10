package com.nebula.customer.adapter.in.web;

import com.nebula.shared.adapter.web.HttpCustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
final class MethodArgumentNotValidExceptionController extends ResponseEntityExceptionHandler {

    public static final String INVALID_METHOD_ARGUMENT_ERROR_CODE = "invalid-method-argument";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        HttpCustomError httpError = new HttpCustomError(INVALID_METHOD_ARGUMENT_ERROR_CODE, exception.getMessage());

        log.error("method argument not valid exception for {}", httpError);

        return handleExceptionInternal(exception, httpError, headers, HttpStatus.BAD_REQUEST, request);
    }

}