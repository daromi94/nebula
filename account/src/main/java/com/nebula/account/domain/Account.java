package com.nebula.account.domain;

import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.account.AccountBalance;
import com.nebula.shared.domain.account.AccountCreated;
import com.nebula.shared.domain.account.AccountId;
import com.nebula.shared.domain.customer.CustomerId;

public final class Account extends AggregateRoot {

    public static final double EMPTY_BALANCE = 0.0;

    private final AccountId id;

    private final CustomerId customerId;

    private final AccountBalance balance;

    public Account(AccountId id, CustomerId customerId, AccountBalance balance) {
        this.id         = id;
        this.customerId = customerId;
        this.balance    = balance;
    }

    public static Account create(AccountId id, CustomerId customerId) {
        Account account = new Account(id, customerId, new AccountBalance(EMPTY_BALANCE));

        account.record(new AccountCreated(id.value(), customerId.value()));

        return account;
    }

    public AccountId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public AccountBalance balance() {
        return balance;
    }

}