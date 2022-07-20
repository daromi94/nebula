package com.nebula.customer.application.port.out;

import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.value.Id;

import java.util.Optional;

public interface CustomerRepository {

  void save(Customer customer);

  Optional<Customer> search(Id id);
}
