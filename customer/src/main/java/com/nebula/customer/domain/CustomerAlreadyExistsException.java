package com.nebula.customer.domain;

import com.nebula.shared.domain.DomainErrorException;
import com.nebula.shared.domain.value.Id;

public final class CustomerAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "customer-already-exists";

    public CustomerAlreadyExistsException(Id id) {
        super(ERROR_CODE, String.format("Customer <%s> already exists!", id.value()));
    }

}