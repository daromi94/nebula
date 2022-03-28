package com.nebula.customer.controller;

import com.nebula.customer.service.CustomerCreator;
import com.nebula.shared.customer.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
public final class CustomerPostController {

    private final CustomerCreator creator;

    public CustomerPostController(CustomerCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public void post(@RequestBody CustomerPostRequest request) {
        log.info("new customer post request for {}", request);
        CustomerId id = new CustomerId(request.id());
        CustomerFirstName firstName = new CustomerFirstName(request.firstName());
        CustomerLastName lastName = new CustomerLastName(request.lastName());
        CustomerEmail email = new CustomerEmail(request.email());
        creator.create(id, firstName, lastName, email);
    }

}