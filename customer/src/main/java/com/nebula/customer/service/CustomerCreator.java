package com.nebula.customer.service;

import com.nebula.customer.domain.Customer;
import com.nebula.customer.repository.CustomerRepository;
import com.nebula.shared.account.domain.AccountCreateMessage;
import com.nebula.shared.amqp.MessagePublisher;
import com.nebula.shared.customer.domain.CustomerEmail;
import com.nebula.shared.customer.domain.CustomerFirstName;
import com.nebula.shared.customer.domain.CustomerId;
import com.nebula.shared.customer.domain.CustomerLastName;
import com.nebula.shared.fraud.FraudCheckClient;
import com.nebula.shared.fraud.domain.FraudCheckResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class CustomerCreator {

    private static final double EMPTY_BALANCE = 0.0;

    private final CustomerRepository repository;

    private final FraudCheckClient fraudCheckClient;

    private final MessagePublisher publisher;

    public CustomerCreator(CustomerRepository repository,
                           FraudCheckClient fraudCheckClient,
                           MessagePublisher publisher) {
        this.repository = repository;
        this.fraudCheckClient = fraudCheckClient;
        this.publisher = publisher;
    }

    public void create(CustomerId id, CustomerFirstName firstName, CustomerLastName lastName, CustomerEmail email) {
        Customer customer = new Customer(id, firstName, lastName, email);
        if (repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("customer already exists!");
        }
        if (isFraudster(email)) {
            throw new IllegalStateException("we have a fraudster!");
        }
        repository.save(customer);
        openAccount(id);
    }

    private boolean isFraudster(CustomerEmail email) {
        FraudCheckResponse response = fraudCheckClient.check(email.getEmail());
        return response.isFraudster();
    }

    private void openAccount(CustomerId id) {
        UUID accountId = UUID.randomUUID();
        AccountCreateMessage message = new AccountCreateMessage(accountId, id.getCustomerId(), EMPTY_BALANCE);
        publisher.publish(message, "internal.exchange", "internal.account.routing-key");
    }

}