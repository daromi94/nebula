package com.amigoscode.customer.service;

import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.fraud.dto.FraudCheckResponse;
import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.api.CustomerRegistrationRequest;
import com.amigoscode.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .customerNumber(request.customerNumber())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getCustomerNumber());
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("we have a fraudster!");
        }
        customerRepository.save(customer);
    }

}