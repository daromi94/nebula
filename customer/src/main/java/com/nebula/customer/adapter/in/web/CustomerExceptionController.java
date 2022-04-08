package com.nebula.customer.adapter.in.web;

import com.nebula.shared.adapter.web.HttpCustomError;
import com.nebula.shared.adapter.web.HttpCustomErrorMapper;
import com.nebula.shared.domain.CustomError;
import com.nebula.shared.domain.DomainErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
final class CustomerExceptionController extends ResponseEntityExceptionHandler {

    private final HttpCustomErrorMapper mapper;

    public CustomerExceptionController(HttpCustomErrorMapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler(DomainErrorException.class)
    public ResponseEntity<HttpCustomError> handleDomainErrorException(DomainErrorException exception) {
        CustomError     error     = exception.error();
        HttpCustomError httpError = mapper.fromDomain(error);

        log.error("domain error exception for {}", error);

        return new ResponseEntity<>(httpError, HttpStatus.BAD_REQUEST);
    }

}