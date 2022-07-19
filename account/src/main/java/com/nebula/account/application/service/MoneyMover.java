package com.nebula.account.application.service;

import com.nebula.account.application.port.in.TransferMoneyCommand;
import com.nebula.account.application.port.in.TransferMoneyUseCase;
import com.nebula.account.application.port.out.SearchAccountPort;
import com.nebula.account.domain.Bundle;
import com.nebula.account.domain.Operation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
final class MoneyMover implements TransferMoneyUseCase {

  private final SearchAccountPort searcher;

  public MoneyMover(SearchAccountPort searcher) {
    this.searcher = searcher;
  }

  @Override
  public Optional<Bundle> transfer(TransferMoneyCommand command) {
    Optional<Operation> withdrawal =
        searcher.search(command.source()).flatMap(acc -> acc.withdraw(command.money()));

    Optional<Operation> deposit =
        searcher.search(command.target()).flatMap(acc -> acc.deposit(command.money()));

    return Optional.of(
        new Bundle(
            command.id(),
            Stream.of(withdrawal, deposit)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList()));
  }
}
