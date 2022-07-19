package com.nebula.account.domain;

import com.nebula.shared.domain.commons.value.CreatedAt;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.Money;

import java.util.Optional;
import java.util.function.BiPredicate;

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

  public Optional<Operation> withdraw(Money cash) {
    BiPredicate<Money, Money> mayWithdraw = Money::isGreaterOrEqualThan;

    if (mayWithdraw.negate().test(balance, cash)) return Optional.empty();

    return Optional.of(new Operation(id, cash.negate()));
  }

  public Optional<Operation> deposit(Money cash) {
    return Optional.of(new Operation(id, cash));
  }

  public Id id() {
    return id;
  }
}
