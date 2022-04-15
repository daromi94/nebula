package com.nebula.shared.domain.value;

public record IsFraudster(boolean value) {

    public static IsFraudster of(boolean value) {
        return new IsFraudster(value);
    }

}