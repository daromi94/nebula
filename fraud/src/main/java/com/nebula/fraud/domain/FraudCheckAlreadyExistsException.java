package com.nebula.fraud.domain;

import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.util.CustomRuntimeException;

public final class FraudCheckAlreadyExistsException extends CustomRuntimeException {

    public static final String ERROR_CODE = "fraud-check-already-exists";

    public FraudCheckAlreadyExistsException(Id id) {
        super(ERROR_CODE, String.format("Fraud check <%s> already exists!", id.value()));
    }

}