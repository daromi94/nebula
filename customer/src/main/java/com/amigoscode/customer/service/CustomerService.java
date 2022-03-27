package com.amigoscode.customer.service;

import com.amigoscode.clients.account.AccountClient;
import com.amigoscode.clients.account.dto.AccountCreationRequest;
import com.amigoscode.clients.account.dto.AccountCreationResponse;
import com.amigoscode.clients.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.clients.customer.dto.CustomerRegistrationResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.fraud.dto.FraudCheckResponse;
import com.amigoscode.customer.domain.Customer;
import com.amigoscode.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient,
                              AccountClient accountClient) {

    public static final double EMPTY_BALANCE = 0.0;

    public CustomerRegistrationResponse registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        if (isFraudster(customer)) {
            throw new IllegalStateException("we have a fraudster!");
        }
        customerRepository.saveAndFlush(customer);
        UUID accountId = openEmptyAccount(customer);
        return new CustomerRegistrationResponse(customer.getId(), accountId);
    }

    private boolean isFraudster(Customer customer) {
        FraudCheckResponse response = fraudClient.isFraudster(customer.getEmail());
        return response.isFraudster();
    }

    private UUID openEmptyAccount(Customer customer) {
        AccountCreationRequest request = new AccountCreationRequest(customer.getId(), EMPTY_BALANCE);
        AccountCreationResponse response = accountClient.createAccount(request);
        return response.accountId();
    }

}