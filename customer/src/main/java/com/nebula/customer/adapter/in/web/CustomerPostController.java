package com.nebula.customer.adapter.in.web;

import com.nebula.customer.application.service.CustomerCreator;
import com.nebula.shared.customer.CustomerPostRequest;
import com.nebula.shared.domain.Email;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
final class CustomerPostController {

    private final CustomerCreator creator;

    public CustomerPostController(CustomerCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody CustomerPostRequest request) {
        log.info("new customer post request for {}", request);

        CustomerId        id        = new CustomerId(request.id());
        CustomerFirstName firstName = new CustomerFirstName(request.firstName());
        CustomerLastName  lastName  = new CustomerLastName(request.lastName());
        Email             email     = new Email(request.email());

        creator.create(id, firstName, lastName, email);
    }

}