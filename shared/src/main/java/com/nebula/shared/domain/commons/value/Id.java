package com.nebula.shared.domain.commons.value;

public record Id(String value) {

    public static Id of(String value) {
        return new Id(value);
    }

}