package com.nebula.customer.domain;

import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.util.CustomRuntimeException;

public final class CustomerAlreadyExistsException extends CustomRuntimeException {

  public static final String ERROR_CODE = "customer-already-exists";

  public CustomerAlreadyExistsException(Id id) {
    super(ERROR_CODE, String.format("Customer <%s> already exists!", id.value()));
  }
}