package com.nebula.account.domain;

import com.nebula.shared.domain.DomainErrorException;
import com.nebula.shared.domain.account.AccountId;

public final class AccountAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "account-already-exists";

    public AccountAlreadyExistsException(AccountId id) {
        super(ERROR_CODE, String.format("Account <%s> already exists!", id.value()));
    }

}