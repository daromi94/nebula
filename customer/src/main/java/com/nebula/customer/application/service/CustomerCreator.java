package com.nebula.customer.application.service;

import com.nebula.customer.application.command.CustomerCreateCommand;
import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.customer.domain.CustomerAlreadyExistsException;
import com.nebula.customer.domain.CustomerIsFraudsterException;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostClient;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostClientErrorException;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostRequest;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostResponse;
import com.nebula.shared.application.service.EventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerCreator {

    private final CustomerRepository repository;

    private final EventPublisher publisher;

    private final FraudChecksPostClient client;

    public CustomerCreator(CustomerRepository repository, EventPublisher publisher, FraudChecksPostClient client) {
        this.repository = repository;
        this.publisher  = publisher;
        this.client     = client;
    }

    public void create(CustomerCreateCommand command) {
        var customer = Customer.create(command);

        repository.search(customer.id()).ifPresent(entity -> {
            throw new CustomerAlreadyExistsException(entity.id());
        });
        if (isFraudster(customer)) {
            throw new CustomerIsFraudsterException(customer.id());
        }

        repository.save(customer);
        publisher.publish(customer.pull());
    }

    private boolean isFraudster(Customer customer) {
        var id        = customer.id().value();
        var firstName = customer.firstName().value();
        var lastName  = customer.lastName().value();
        var email     = customer.email().value();
        var request   = new FraudChecksPostRequest(id, firstName, lastName, email);

        FraudChecksPostResponse response;

        try {
            response = client.post(request);
        } catch (RuntimeException exception) {
            throw new FraudChecksPostClientErrorException(exception);
        }

        return response.isFraudster();
    }

}