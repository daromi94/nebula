package com.nebula.account.application.port.out;

import com.nebula.account.domain.Account;

public interface SaveAccountPort {

  void save(Account account);
}
