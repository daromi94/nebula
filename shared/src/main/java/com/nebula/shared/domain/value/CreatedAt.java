package com.nebula.shared.domain.value;

import java.time.LocalDateTime;

public record CreatedAt(LocalDateTime value) {

  public static final CreatedAt NOW = new CreatedAt(LocalDateTime.now());
}
