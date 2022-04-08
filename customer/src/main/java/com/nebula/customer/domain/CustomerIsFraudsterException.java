package com.nebula.customer.domain;

import com.nebula.shared.domain.DomainErrorException;
import com.nebula.shared.domain.customer.CustomerId;

public final class CustomerIsFraudsterException extends DomainErrorException {

    public static final String ERROR_CODE = "customer-is-fraudster";

    public CustomerIsFraudsterException(CustomerId id) {
        super(ERROR_CODE, String.format("Customer <%s> is a fraudster!", id.value()));
    }

}