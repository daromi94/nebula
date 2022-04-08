package com.nebula.customer.adapter.in.web;

import com.nebula.customer.application.service.CustomerCreator;
import com.nebula.shared.adapter.web.customer.CustomerPostRequest;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void post(@RequestBody @Valid CustomerPostRequest request) {
        log.info("new customer post request for {}", request);

        CustomerId        id        = new CustomerId(request.getId());
        CustomerFirstName firstName = new CustomerFirstName(request.getFirstName());
        CustomerLastName  lastName  = new CustomerLastName(request.getLastName());
        EmailAddress      email     = new EmailAddress(request.getEmail());

        creator.create(id, firstName, lastName, email);
    }

}