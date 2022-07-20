package com.nebula.shared.domain.value;

public record Money(Double value) {

  public static final Money ZERO = new Money(0.0);

  public Money negate() {
    return new Money(-1.0 * value);
  }

  public boolean isGreaterOrEqualThan(Money money) {
    return value >= money.value;
  }
}
