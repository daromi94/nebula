package com.nebula.shared.domain.value;

public record FirstName(String value) {

    public static FirstName of(String value) {
        return new FirstName(value);
    }

}