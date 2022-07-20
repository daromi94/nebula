package com.nebula.account.application.service;

import com.nebula.account.application.port.in.TransferMoneyCommand;
import com.nebula.account.application.port.in.TransferMoneyUseCase;
import com.nebula.account.application.port.out.SearchAccountPort;
import com.nebula.account.domain.Account;
import com.nebula.account.domain.Bundle;
import com.nebula.account.domain.Operation;
import com.nebula.shared.domain.value.Id;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.BiFunction;

@Service
@Transactional
final class MoneyMover implements TransferMoneyUseCase {

  private final SearchAccountPort searcher;

  MoneyMover(SearchAccountPort searcher) {
    this.searcher = searcher;
  }

  @Override
  public void transfer(TransferMoneyCommand command) {
    BiFunction<Id, SearchAccountPort, Account> fetchAccount =
        (id, fetcher) ->
            fetcher
                .search(id)
                .orElseThrow(
                    () -> {
                      // TODO: throw a dedicated exception
                      throw new RuntimeException();
                    });

    Account source = fetchAccount.apply(command.source(), searcher);
    Account target = fetchAccount.apply(command.target(), searcher);

    Operation withdrawal = source.withdraw(command.money());
    Operation deposit = target.deposit(command.money());

    Bundle bundle = new Bundle(command.id(), List.of(withdrawal, deposit));
    // TODO: save the bundle using a port
  }
}
