package com.nebula.customer.adapter.in.web;

import com.nebula.shared.exception.CustomError;
import com.nebula.shared.exception.DomainErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
final class CustomerExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainErrorException.class)
    public ResponseEntity<CustomError> handleDomainErrorException(DomainErrorException exception) {
        return new ResponseEntity<>(exception.error(), HttpStatus.CONFLICT);
    }

}