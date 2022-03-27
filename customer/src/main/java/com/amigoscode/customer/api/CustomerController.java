package com.amigoscode.customer.api;

import com.amigoscode.clients.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.clients.customer.dto.CustomerRegistrationResponse;
import com.amigoscode.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public CustomerRegistrationResponse registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        log.info("new customer registration request for {}", request);
        return customerService.registerCustomer(request);
    }

}