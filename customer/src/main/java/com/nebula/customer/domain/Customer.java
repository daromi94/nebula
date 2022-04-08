package com.nebula.customer.domain;

import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.customer.CustomerCreated;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;

public final class Customer extends AggregateRoot {

    private final CustomerId id;

    private final CustomerFirstName firstName;

    private final CustomerLastName lastName;

    private final EmailAddress email;

    public Customer(CustomerId id, CustomerFirstName firstName, CustomerLastName lastName, EmailAddress email) {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }

    public static Customer create(CustomerId id,
                                  CustomerFirstName firstName,
                                  CustomerLastName lastName,
                                  EmailAddress email) {
        Customer customer = new Customer(id, firstName, lastName, email);

        customer.record(new CustomerCreated(id.value(), firstName.value(), lastName.value(), email.value()));

        return customer;
    }

    public CustomerId id() {
        return id;
    }

    public CustomerFirstName firstName() {
        return firstName;
    }

    public CustomerLastName lastName() {
        return lastName;
    }

    public EmailAddress email() {
        return email;
    }

}