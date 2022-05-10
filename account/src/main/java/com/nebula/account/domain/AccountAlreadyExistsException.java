package com.nebula.account.domain;

import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.util.CustomRuntimeException;

public final class AccountAlreadyExistsException extends CustomRuntimeException {

  public static final String ERROR_CODE = "account-already-exists";

  public AccountAlreadyExistsException(Id id) {
    super(ERROR_CODE, String.format("Account <%s> already exists!", id.value()));
  }
}
