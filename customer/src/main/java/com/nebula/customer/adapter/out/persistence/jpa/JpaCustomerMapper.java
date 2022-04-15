package com.nebula.customer.adapter.out.persistence.jpa;

import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;

public class JpaCustomerMapper {

    public Customer fromJpa(JpaCustomer jpaCustomer) {
        var id        = new Id(jpaCustomer.getId());
        var firstName = new FirstName(jpaCustomer.getFirstName());
        var lastName  = new LastName(jpaCustomer.getLastName());
        var email     = new EmailAddress(jpaCustomer.getEmail());

        return new Customer(id, firstName, lastName, email);
    }

    public JpaCustomer fromDomain(Customer customer) {
        var id        = customer.id().value();
        var firstName = customer.firstName().value();
        var lastName  = customer.lastName().value();
        var email     = customer.email().value();

        return new JpaCustomer(id, firstName, lastName, email);
    }

}