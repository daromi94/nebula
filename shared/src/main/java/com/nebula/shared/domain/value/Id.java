package com.nebula.shared.domain.value;

public record Id(String value) {

    public static Id of(String value) {
        return new Id(value);
    }

}