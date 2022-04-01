package com.nebula.account.domain;

import com.nebula.shared.domain.DomainError;
import com.nebula.shared.domain.account.AccountId;

public final class AccountAlreadyExists extends DomainError {

    public static final String ERROR_CODE = "account_already_exists";

    public AccountAlreadyExists(AccountId id) {
        super(ERROR_CODE, String.format("Account <%s> already exists!", id.value()));
    }

}