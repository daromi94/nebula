package com.nebula.shared.domain.commons.value;

public record EmailAddress(String value) {

    public static EmailAddress of(String value) {
        return new EmailAddress(value);
    }

}