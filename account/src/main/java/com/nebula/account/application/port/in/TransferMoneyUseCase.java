package com.nebula.account.application.port.in;

public interface TransferMoneyUseCase {

  void transfer(TransferMoneyCommand command);
}
