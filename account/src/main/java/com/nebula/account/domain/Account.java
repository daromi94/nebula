package com.nebula.account.domain;

import com.nebula.shared.domain.commons.value.CreatedAt;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.Money;

import java.util.function.BiConsumer;

public final class Account {

  private final Id id;

  private final Id userId;

  private final CreatedAt createdAt;

  private final Money balance;

  private Account(Id id, Id userId, Money balance, CreatedAt createdAt) {
    this.id = id;
    this.userId = userId;
    this.balance = balance;
    this.createdAt = createdAt;
  }

  public static Account of(Id id, Id userId) {
    return new Account(id, userId, Money.ZERO, CreatedAt.NOW);
  }

  public Operation withdraw(Money cash) {
    BiConsumer<Money, Money> mayWithdraw =
        (funds, money) -> {
          // TODO: throw a dedicated exception
          if (!funds.isGreaterOrEqualThan(money)) throw new RuntimeException();
        };

    mayWithdraw.accept(balance, cash);

    return new Operation(id, cash.negate());
  }

  public Operation deposit(Money cash) {
    return new Operation(id, cash);
  }

  public Id id() {
    return id;
  }
}
