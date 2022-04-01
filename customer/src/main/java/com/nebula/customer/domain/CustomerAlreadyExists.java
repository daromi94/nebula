package com.nebula.customer.domain;

import com.nebula.shared.domain.DomainError;
import com.nebula.shared.domain.customer.CustomerId;

public final class CustomerAlreadyExists extends DomainError {

    public static final String ERROR_CODE = "customer_already_exists";

    public CustomerAlreadyExists(CustomerId id) {
        super(ERROR_CODE, String.format("Customer <%s> already exists!", id.value()));
    }

}