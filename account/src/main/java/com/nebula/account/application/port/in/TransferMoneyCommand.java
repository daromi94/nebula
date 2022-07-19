package com.nebula.account.application.port.in;

import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.Money;

import javax.validation.constraints.NotNull;

public final class TransferMoneyCommand {

  @NotNull private final Id id;

  @NotNull private final Id source;

  @NotNull private final Id target;

  @NotNull private final Money money;

  public TransferMoneyCommand(Id id, Id source, Id target, Money money) {
    this.id = id;
    this.source = source;
    this.target = target;
    this.money = money;
  }

  public Id id() {
    return id;
  }

  public Id source() {
    return source;
  }

  public Id target() {
    return target;
  }

  public Money money() {
    return money;
  }
}
