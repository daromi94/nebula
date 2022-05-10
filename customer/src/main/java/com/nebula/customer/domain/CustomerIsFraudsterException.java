package com.nebula.customer.domain;

import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.util.CustomRuntimeException;

public final class CustomerIsFraudsterException extends CustomRuntimeException {

  public static final String ERROR_CODE = "customer-is-fraudster";

  public CustomerIsFraudsterException(Id id) {
    super(ERROR_CODE, String.format("Customer <%s> is a fraudster!", id.value()));
  }
}