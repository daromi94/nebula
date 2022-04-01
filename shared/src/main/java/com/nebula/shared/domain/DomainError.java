package com.nebula.shared.domain;

public abstract class DomainError extends RuntimeException {

    private final String code;

    private final String message;

    public DomainError(String code, String message) {
        super(message);

        this.code    = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

}