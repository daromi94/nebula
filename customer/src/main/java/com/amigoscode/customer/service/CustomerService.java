package com.amigoscode.customer.service;

import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.domain.CustomerRegistrationRequest;
import com.amigoscode.customer.domain.FraudCheckResponse;
import com.amigoscode.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .customerNumber(request.customerNumber())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerNumber}",
                FraudCheckResponse.class,
                customer.getCustomerNumber());
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("we have a fraudster!");
        }
        customerRepository.save(customer);
    }

}