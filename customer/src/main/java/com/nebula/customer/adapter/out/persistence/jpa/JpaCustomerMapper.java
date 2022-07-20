package com.nebula.customer.adapter.out.persistence.jpa;

import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.value.Id;

public class JpaCustomerMapper {

  public Customer fromJpa(JpaCustomer jpaCustomer) {
    var id = Id.of(jpaCustomer.getId());
    var firstName = FirstName.of(jpaCustomer.getFirstName());
    var lastName = LastName.of(jpaCustomer.getLastName());
    var email = EmailAddress.of(jpaCustomer.getEmail());

    return new Customer(id, firstName, lastName, email);
  }

  public JpaCustomer fromDomain(Customer customer) {
    var id = customer.id().value();
    var firstName = customer.firstName().value();
    var lastName = customer.lastName().value();
    var email = customer.email().value();

    return new JpaCustomer(id, firstName, lastName, email);
  }
}
