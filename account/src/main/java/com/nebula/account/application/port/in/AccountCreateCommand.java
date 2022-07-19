package com.nebula.account.application.port.in;

import com.nebula.shared.domain.commons.value.Id;

import javax.validation.constraints.NotNull;

public final class AccountCreateCommand {

  @NotNull private final Id id;

  @NotNull private final Id userId;

  public AccountCreateCommand(Id id, Id userId) {
    this.id = id;
    this.userId = userId;
  }

  public Id id() {
    return id;
  }

  public Id userId() {
    return userId;
  }
}
