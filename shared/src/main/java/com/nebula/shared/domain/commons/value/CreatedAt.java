package com.nebula.shared.domain.commons.value;

import java.time.LocalDateTime;

public record CreatedAt(LocalDateTime value) {

    public static CreatedAt of(LocalDateTime value) {
        return new CreatedAt(value);
    }

}