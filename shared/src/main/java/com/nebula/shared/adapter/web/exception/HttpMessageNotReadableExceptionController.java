package com.nebula.shared.adapter.web.exception;

import com.nebula.shared.adapter.web.HttpCustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
final class HttpMessageNotReadableExceptionController {

    public static final String ERROR_CODE = "http-message-not-readable";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpCustomError> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        var message   = exception.getMostSpecificCause().getMessage();
        var httpError = new HttpCustomError(ERROR_CODE, message);

        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

}