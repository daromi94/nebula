package com.nebula.shared.exception;

public abstract class DomainErrorException extends RuntimeException {

    private final CustomError error;

    public DomainErrorException(String code, String message) {
        super(message);

        this.error = new CustomError(code, message);
    }

    public CustomError error() {
        return error;
    }

}