package com.nebula.account.application.service;

import com.nebula.account.application.port.in.AccountCreateCommand;
import com.nebula.account.application.port.in.AccountCreateUseCase;
import com.nebula.account.application.port.out.SaveAccountPort;
import com.nebula.account.application.port.out.SearchAccountPort;
import com.nebula.account.domain.Account;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
final class AccountCreator implements AccountCreateUseCase {

  private final SearchAccountPort searcher;

  private final SaveAccountPort saver;

  public AccountCreator(SearchAccountPort searcher, SaveAccountPort saver) {
    this.searcher = searcher;
    this.saver = saver;
  }

  @Override
  public Optional<Account> create(AccountCreateCommand command) {
    if (searcher.exists(command.id())) {
      // TODO: throw a dedicated exception
      throw new RuntimeException();
    }

    Account account = Account.of(command.id(), command.userId());

    saver.save(account);

    return Optional.of(account);
  }
}
