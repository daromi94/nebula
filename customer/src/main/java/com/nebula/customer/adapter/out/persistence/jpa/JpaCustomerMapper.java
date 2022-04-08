package com.nebula.customer.adapter.out.persistence.jpa;

import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;

public class JpaCustomerMapper {

    public Customer fromJpa(JpaCustomer jpaCustomer) {
        CustomerId        id        = new CustomerId(jpaCustomer.getId());
        CustomerFirstName firstName = new CustomerFirstName(jpaCustomer.getFirstName());
        CustomerLastName  lastName  = new CustomerLastName(jpaCustomer.getLastName());
        EmailAddress      email     = new EmailAddress(jpaCustomer.getEmail());

        return new Customer(id, firstName, lastName, email);
    }

    public JpaCustomer fromDomain(Customer customer) {
        String id        = customer.id().value();
        String firstName = customer.firstName().value();
        String lastName  = customer.lastName().value();
        String email     = customer.email().value();

        return new JpaCustomer(id, firstName, lastName, email);
    }

}