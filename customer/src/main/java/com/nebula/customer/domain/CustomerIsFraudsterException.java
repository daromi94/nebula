package com.nebula.customer.domain;

import com.nebula.shared.domain.customer.CustomerId;
import com.nebula.shared.exception.DomainErrorException;

public final class CustomerIsFraudsterException extends DomainErrorException {

    public static final String ERROR_CODE = "customer_is_fraudster";

    public CustomerIsFraudsterException(CustomerId id) {
        super(ERROR_CODE, String.format("Customer <%s> is a fraudster!", id.value()));
    }

}