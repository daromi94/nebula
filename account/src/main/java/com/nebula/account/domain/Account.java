package com.nebula.account.domain;

import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.account.AccountCreatedEvent;
import com.nebula.shared.domain.value.Balance;
import com.nebula.shared.domain.value.Id;

public final class Account extends AggregateRoot {

    public static final double EMPTY_BALANCE = 0.0;

    private final Id id;

    private final Id customerId;

    private final Balance balance;

    public Account(Id id, Id customerId, Balance balance) {
        this.id         = id;
        this.customerId = customerId;
        this.balance    = balance;
    }

    public static Account create(Id id, Id customerId) {
        var account = new Account(id, customerId, new Balance(EMPTY_BALANCE));

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

}