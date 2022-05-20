package com.nebula.customer.application.service;

import com.nebula.customer.application.command.CustomerCreateCommand;
import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.customer.domain.CustomerAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerCreator {

  private final CustomerRepository repository;

  private final EventPublisher publisher;

  public CustomerCreator(CustomerRepository repository, EventPublisher publisher) {
    this.repository = repository;
    this.publisher = publisher;
  }

  public void create(CustomerCreateCommand command) {
    var customer = Customer.create(command);

    repository
        .search(customer.id())
        .ifPresent(
            entity -> {
              throw new CustomerAlreadyExistsException(entity.id());
            });

    repository.save(customer);
    publisher.publish(customer.pull());
  }
}
