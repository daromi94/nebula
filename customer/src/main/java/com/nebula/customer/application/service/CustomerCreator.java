package com.nebula.customer.application.service;

import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.customer.domain.CustomerAlreadyExists;
import com.nebula.customer.domain.CustomerIsFraudster;
import com.nebula.shared.amqp.EventBus;
import com.nebula.shared.domain.Email;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;
import com.nebula.shared.fraud.FraudCheckClient;
import com.nebula.shared.fraud.FraudCheckResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerCreator {

    private final CustomerRepository repository;

    private final EventBus eventBus;

    private final FraudCheckClient fraudCheckClient;

    public CustomerCreator(CustomerRepository repository, EventBus eventBus, FraudCheckClient fraudCheckClient) {
        this.repository       = repository;
        this.eventBus         = eventBus;
        this.fraudCheckClient = fraudCheckClient;
    }

    public void create(CustomerId id,
                       CustomerFirstName firstName,
                       CustomerLastName lastName,
                       Email email) throws CustomerAlreadyExists, CustomerIsFraudster {
        Customer customer = Customer.create(id, firstName, lastName, email);

        repository.search(id).ifPresent((entity) -> {
            throw new CustomerAlreadyExists(id);
        });
        if (isFraudster(email)) {
            throw new CustomerIsFraudster(id);
        }

        repository.save(customer);
        // TODO: Publish domain events to the event bus
    }

    private boolean isFraudster(Email email) {
        FraudCheckResponse response = fraudCheckClient.check(email.value());

        return response.isFraudster();
    }

}