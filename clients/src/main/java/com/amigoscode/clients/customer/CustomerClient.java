package com.amigoscode.clients.customer;

import com.amigoscode.clients.customer.dto.CustomerRegistrationRequest;
import com.amigoscode.clients.customer.dto.CustomerRegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "customer", path = "api/v1/customers")
public interface CustomerClient {

    @PostMapping
    CustomerRegistrationResponse registerCustomer(@RequestBody CustomerRegistrationRequest request);

}