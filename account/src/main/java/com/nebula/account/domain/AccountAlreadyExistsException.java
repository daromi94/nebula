package com.nebula.account.domain;

import com.nebula.shared.domain.account.AccountId;
import com.nebula.shared.exception.DomainErrorException;

public final class AccountAlreadyExistsException extends DomainErrorException {

    public static final String ERROR_CODE = "account_already_exists";

    public AccountAlreadyExistsException(AccountId id) {
        super(ERROR_CODE, String.format("Account <%s> already exists!", id.value()));
    }

}