package com.nebula.customer.application.service;

import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.customer.domain.CustomerAlreadyExistsException;
import com.nebula.customer.domain.CustomerIsFraudsterException;
import com.nebula.shared.adapter.web.fraud.FraudCheckClient;
import com.nebula.shared.adapter.web.fraud.FraudCheckRequest;
import com.nebula.shared.adapter.web.fraud.FraudCheckResponse;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerCreator {

    private final CustomerRepository repository;

    private final EventPublisher publisher;

    private final FraudCheckClient fraudCheckClient;

    public CustomerCreator(CustomerRepository repository, EventPublisher publisher, FraudCheckClient fraudCheckClient) {
        this.repository       = repository;
        this.publisher        = publisher;
        this.fraudCheckClient = fraudCheckClient;
    }

    public void create(CustomerId id,
                       CustomerFirstName firstName,
                       CustomerLastName lastName,
                       EmailAddress email) throws CustomerAlreadyExistsException, CustomerIsFraudsterException {
        Customer customer = Customer.create(id, firstName, lastName, email);

        repository.search(id).ifPresent(entity -> {
            throw new CustomerAlreadyExistsException(id);
        });
        if (isFraudster(email)) {
            throw new CustomerIsFraudsterException(id);
        }

        repository.save(customer);
        publisher.publish(customer.pull());
    }

    private boolean isFraudster(EmailAddress email) {
        FraudCheckRequest  request  = new FraudCheckRequest(email.value());
        FraudCheckResponse response = fraudCheckClient.check(request);

        return response.isFraudster();
    }

}