package com.nebula.shared.domain.customer;

import com.nebula.shared.domain.bus.event.DomainEvent;

public final class CustomerCreated extends DomainEvent {

    private final String firstName;

    private final String lastName;

    private final String email;

    public CustomerCreated(String id, String firstName, String lastName, String email) {
        super(id);

        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }

    @Override
    public String name() {
        return "customer.created";
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String email() {
        return email;
    }

}