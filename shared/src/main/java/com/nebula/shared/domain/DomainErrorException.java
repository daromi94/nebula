package com.nebula.shared.domain;

public abstract class DomainErrorException extends RuntimeException {

    private final CustomError error;

    protected DomainErrorException(String code, String message) {
        super(message);

        this.error = new CustomError(code, message);
    }

    public CustomError error() {
        return error;
    }

}