package com.nebula.customer.adapter.out.persistence.jpa;

import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;

public class JpaCustomerMapper {

    public Customer fromJpa(JpaCustomer jpaCustomer) {
        Id           id        = new Id(jpaCustomer.getId());
        FirstName    firstName = new FirstName(jpaCustomer.getFirstName());
        LastName     lastName  = new LastName(jpaCustomer.getLastName());
        EmailAddress email     = new EmailAddress(jpaCustomer.getEmail());

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