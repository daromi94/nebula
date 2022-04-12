package com.nebula.shared.adapter.web.exception;

import com.nebula.shared.adapter.web.HttpCustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
final class MethodArgumentNotValidExceptionController {

    public static final String ERROR_CODE = "invalid-method-argument";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<HttpCustomError>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();

        List<HttpCustomError> httpErrors = fieldErrors.stream()
                .map(error -> new HttpCustomError(ERROR_CODE, error.getDefaultMessage()))
                .toList();

        return new ResponseEntity<>(httpErrors, HttpStatus.BAD_REQUEST);
    }

}