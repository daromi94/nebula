package com.nebula.shared.adapter.web.commons.advice;

import com.nebula.shared.util.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
final class HttpMessageNotReadableExceptionController {

  public static final String ERROR_CODE = "http-message-not-readable";

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<CustomError> handle(HttpMessageNotReadableException exception) {
    var message = exception.getMostSpecificCause().getMessage();
    var error = new CustomError(ERROR_CODE, message);

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}