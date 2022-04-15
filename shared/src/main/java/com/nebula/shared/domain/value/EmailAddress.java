package com.nebula.shared.domain.value;

public record EmailAddress(String value) {

    public static EmailAddress of(String value) {
        return new EmailAddress(value);
    }

}