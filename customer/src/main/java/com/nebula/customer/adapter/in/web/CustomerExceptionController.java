package com.nebula.customer.adapter.in.web;

import com.nebula.shared.adapter.web.HttpCustomError;
import com.nebula.shared.domain.CustomError;
import com.nebula.shared.domain.DomainErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
final class CustomerExceptionController extends ResponseEntityExceptionHandler {

    public static final String INVALID_METHOD_ARGUMENT_ERROR_CODE = "invalid-method-argument";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        HttpCustomError httpError = new HttpCustomError(INVALID_METHOD_ARGUMENT_ERROR_CODE, exception.getMessage());

        log.error("invalid method argument exception for {}", httpError);

        return handleExceptionInternal(exception, httpError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DomainErrorException.class)
    public ResponseEntity<HttpCustomError> handleDomainErrorException(DomainErrorException exception) {
        CustomError     error     = exception.error();
        HttpCustomError httpError = new HttpCustomError(error.code(), error.message());

        log.error("domain error exception for {}", error);

        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

}