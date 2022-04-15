package com.nebula.shared.adapter.web.exception;

import com.nebula.shared.adapter.web.HttpCustomError;
import com.nebula.shared.domain.DomainErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
final class DomainErrorExceptionController {

    @ExceptionHandler(DomainErrorException.class)
    public ResponseEntity<HttpCustomError> handleDomainErrorException(DomainErrorException exception) {
        var error     = exception.error();
        var httpError = new HttpCustomError(error.code(), error.message());

        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

}