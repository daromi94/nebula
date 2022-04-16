package com.nebula.shared.domain.commons.value;

public record LastName(String value) {

    public static LastName of(String value) {
        return new LastName(value);
    }

}