package com.nebula.account.domain;

import com.nebula.shared.domain.account.AccountCreatedEvent;
import com.nebula.shared.domain.commons.AggregateRoot;
import com.nebula.shared.domain.commons.value.Balance;
import com.nebula.shared.domain.commons.value.Id;

public final class Account extends AggregateRoot {

  public static final double EMPTY_BALANCE = 0.0;

  private final Id id;

  private final Id customerId;

  private final Balance balance;

  private final AccountStatus status;

  public Account(Id id, Id customerId, Balance balance, AccountStatus status) {
    this.id = id;
    this.customerId = customerId;
    this.balance = balance;
    this.status = status;
  }

  public static Account create(Id id, Id customerId) {
    var account =
        new Account(id, customerId, new Balance(EMPTY_BALANCE), AccountStatus.PENDING_APPROVAL);

    account.register(new AccountCreatedEvent(id, customerId));

    return account;
  }

  public Id id() {
    return id;
  }

  public Id customerId() {
    return customerId;
  }

  public Balance balance() {
    return balance;
  }

  public AccountStatus status() {
    return status;
  }
}
