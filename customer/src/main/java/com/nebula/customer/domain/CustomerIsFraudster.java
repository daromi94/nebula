package com.nebula.customer.domain;

import com.nebula.shared.domain.DomainError;
import com.nebula.shared.domain.customer.CustomerId;

public final class CustomerIsFraudster extends DomainError {

    public static final String ERROR_CODE = "customer_is_fraudster";

    public CustomerIsFraudster(CustomerId id) {
        super(ERROR_CODE, String.format("Customer <%s> is a fraudster!", id.value()));
    }

}