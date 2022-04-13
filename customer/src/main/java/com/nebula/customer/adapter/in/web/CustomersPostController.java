package com.nebula.customer.adapter.in.web;

import com.nebula.customer.application.service.CustomerCreator;
import com.nebula.shared.adapter.web.customer.CustomersPostRequest;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.request-mappings.customers-post}")
@Slf4j
final class CustomersPostController {

    private final CustomerCreator creator;

    public CustomersPostController(CustomerCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody @Valid CustomersPostRequest request) {
        log.info("customers post request for {}", request);

        Id           id        = new Id(request.getId());
        FirstName    firstName = new FirstName(request.getFirstName());
        LastName     lastName  = new LastName(request.getLastName());
        EmailAddress email     = new EmailAddress(request.getEmail());

        creator.create(id, firstName, lastName, email);
    }

}