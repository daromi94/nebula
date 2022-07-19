package com.nebula.account.application.port.in;

import com.nebula.account.domain.Bundle;
import com.nebula.shared.domain.commons.value.Id;

import java.util.Optional;

public interface TransferMoneyUseCase {

  Optional<Bundle> transfer(TransferMoneyCommand command);
}
