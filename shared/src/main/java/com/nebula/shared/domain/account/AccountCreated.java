package com.nebula.shared.domain.account;

import com.nebula.shared.domain.event.DomainEvent;

public final class AccountCreated extends DomainEvent {

    private final String customerId;

    private final double balance;

    public AccountCreated(String id, String customerId, double balance) {
        super(id);

        this.customerId = customerId;
        this.balance    = balance;
    }

    @Override
    public String name() {
        return "account.created";
    }

    public String customerId() {
        return customerId;
    }

    public double balance() {
        return balance;
    }

}