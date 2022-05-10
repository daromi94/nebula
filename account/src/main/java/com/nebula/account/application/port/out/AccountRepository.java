package com.nebula.account.application.port.out;

import com.nebula.account.domain.Account;
import com.nebula.shared.domain.commons.value.Id;
import java.util.Optional;

public interface AccountRepository {

  void save(Account account);

  Optional<Account> search(Id id);
}