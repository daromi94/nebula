package com.nebula.shared.adapter.web.fraud;

import com.nebula.shared.util.CustomRuntimeException;

public final class FraudChecksPostClientErrorException extends CustomRuntimeException {

    public static final String ERROR_CODE = "fraud-checks-post-client-failed";

    public FraudChecksPostClientErrorException(Throwable cause) {
        super(ERROR_CODE, "Fraud-checks post client failed!", cause);
    }

}