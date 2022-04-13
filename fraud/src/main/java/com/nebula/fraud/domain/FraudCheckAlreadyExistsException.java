package com.nebula.fraud.domain;

import com.nebula.shared.domain.DomainErrorException;
import com.nebula.shared.domain.value.Id;

public final class FraudCheckAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "fraud-check-already-exists";

    public FraudCheckAlreadyExistsException(Id id) {
        super(ERROR_CODE, String.format("Fraud check <%s> already exists!", id.value()));
    }

}