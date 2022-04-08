package com.nebula.customer.domain;

import com.nebula.shared.domain.DomainErrorException;
import com.nebula.shared.domain.customer.CustomerId;

public final class CustomerAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "customer_already_exists";

    public CustomerAlreadyExistsException(CustomerId id) {
        super(ERROR_CODE, String.format("Customer <%s> already exists!", id.value()));
    }

}