package com.nebula.customer.domain;

import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.exception.DomainErrorException;

public final class CustomerAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "customer_already_exists";

    public CustomerAlreadyExistsException(CustomerId id) {
        super(ERROR_CODE, String.format("Customer <%s> already exists!", id.value()));
    }

}