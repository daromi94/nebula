package com.nebula.customer.adapter.in;

import com.nebula.customer.application.service.CustomerCreator;
import com.nebula.shared.customer.CustomerPostRequest;
import com.nebula.shared.domain.Email;
import com.nebula.shared.domain.customer.CustomerFirstName;
import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.domain.customer.CustomerLastName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
final class CustomerPostController {

    private final CustomerCreator creator;

    public CustomerPostController(CustomerCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public void post(@RequestBody CustomerPostRequest request) {
        log.info("new customer post request for {}", request);

        CustomerId        id        = new CustomerId(request.id());
        CustomerFirstName firstName = new CustomerFirstName(request.firstName());
        CustomerLastName  lastName  = new CustomerLastName(request.lastName());
        Email             email     = new Email(request.email());

        creator.create(id, firstName, lastName, email);
    }

}