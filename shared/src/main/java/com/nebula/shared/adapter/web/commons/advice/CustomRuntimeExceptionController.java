package com.nebula.shared.adapter.web.commons.advice;

import com.nebula.shared.util.CustomError;
import com.nebula.shared.util.CustomRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
final class CustomRuntimeExceptionController {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<CustomError> handle(CustomRuntimeException exception) {
        return new ResponseEntity<>(exception.error(), HttpStatus.BAD_REQUEST);
    }

}