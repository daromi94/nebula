package com.nebula.shared.domain.value;

public record Balance(double value) {

    public static Balance of(double value) {
        return new Balance(value);
    }

}