package com.nebula.fraud.application.port.out;

import com.nebula.fraud.domain.FraudCheck;
import com.nebula.shared.domain.commons.value.Id;
import java.util.Optional;

public interface FraudCheckRepository {

  void save(FraudCheck fraudCheck);

  Optional<FraudCheck> search(Id id);
}