package com.nebula.shared.adapter.web.commons.advice;

import com.nebula.shared.util.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
final class MethodArgumentNotValidExceptionController {

    public static final String ERROR_CODE = "invalid-method-argument";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CustomError>> handle(MethodArgumentNotValidException exception) {
        var fieldErrors = exception.getFieldErrors();

        var errors = fieldErrors.stream().map(error -> new CustomError(ERROR_CODE, error.getDefaultMessage())).toList();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}