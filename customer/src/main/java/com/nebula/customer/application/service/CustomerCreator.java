package com.nebula.customer.application.service;

import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.customer.domain.CustomerAlreadyExistsException;
import com.nebula.customer.domain.CustomerIsFraudsterException;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostClient;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostRequest;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostResponse;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;
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

    public void create(Id id,
                       FirstName firstName,
                       LastName lastName,
                       EmailAddress email) throws CustomerAlreadyExistsException, CustomerIsFraudsterException {
        Customer customer = Customer.create(id, firstName, lastName, email);

        repository.search(id).ifPresent(entity -> {
            throw new CustomerAlreadyExistsException(id);
        });
        if (isFraudster(customer)) {
            throw new CustomerIsFraudsterException(id);
        }

        repository.save(customer);
        publisher.publish(customer.pull());
    }

    private boolean isFraudster(Customer customer) {
        String id        = customer.id().value();
        String firstName = customer.firstName().value();
        String lastName  = customer.lastName().value();
        String email     = customer.email().value();

        FraudChecksPostRequest  request  = new FraudChecksPostRequest(id, firstName, lastName, email);
        FraudChecksPostResponse response = client.post(request);

        return response.isFraudster();
    }

}