package com.nebula.account.application.port.in;

import com.nebula.account.domain.Account;

import java.util.Optional;

public interface AccountCreateUseCase {

  Optional<Account> create(AccountCreateCommand command);
}
