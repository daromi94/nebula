package com.nebula.account.domain;

import com.nebula.shared.domain.DomainErrorException;
import com.nebula.shared.domain.value.Id;

public final class AccountAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "account-already-exists";

    public AccountAlreadyExistsException(Id id) {
        super(ERROR_CODE, String.format("Account <%s> already exists!", id.value()));
    }

}