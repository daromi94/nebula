package com.nebula.customer.adapter.in.web;

import com.nebula.customer.application.command.CustomerCreateCommandMapper;
import com.nebula.customer.application.service.CustomerCreator;
import com.nebula.shared.adapter.web.customer.CustomersPostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.request-mappings.customers-post}")
@Slf4j
final class CustomersPostController {

    private final CustomerCreator creator;

    private final CustomerCreateCommandMapper mapper;

    public CustomersPostController(CustomerCreator creator, CustomerCreateCommandMapper mapper) {
        this.creator = creator;
        this.mapper  = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody @Valid CustomersPostRequest request) {
        log.info("customers post request for {}", request);

        var command = mapper.fromRequest(request);

        creator.create(command);
    }

}