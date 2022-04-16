package com.nebula.shared.domain.commons.value;

public record FirstName(String value) {

    public static FirstName of(String value) {
        return new FirstName(value);
    }

}