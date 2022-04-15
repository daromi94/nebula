package com.nebula.customer.domain;

import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.customer.CustomerCreatedEvent;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;

public final class Customer extends AggregateRoot {

    private final Id id;

    private final FirstName firstName;

    private final LastName lastName;

    private final EmailAddress email;

    public Customer(Id id, FirstName firstName, LastName lastName, EmailAddress email) {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }

    public static Customer create(Id id, FirstName firstName, LastName lastName, EmailAddress email) {
        var customer = new Customer(id, firstName, lastName, email);

        customer.register(new CustomerCreatedEvent(id, firstName, lastName, email));

        return customer;
    }

    public Id id() {
        return id;
    }

    public FirstName firstName() {
        return firstName;
    }

    public LastName lastName() {
        return lastName;
    }

    public EmailAddress email() {
        return email;
    }

}