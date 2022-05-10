package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.IsFraudster;
import com.nebula.shared.domain.commons.value.LastName;
import java.util.function.Predicate;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FraudDetector {

  private final FraudsterRepository repository;

  public FraudDetector(FraudsterRepository repository) {
    this.repository = repository;
  }

  public IsFraudster detect(FirstName firstName, LastName lastName, EmailAddress email) {
    Predicate<Fraudster> hasSameName =
        fraudster -> {
          var sameFirst = fraudster.firstName().equals(firstName);
          var sameLast = fraudster.lastName().equals(lastName);

          return sameFirst && sameLast;
        };

    var count =
        repository.search().stream()
            .filter(fraudster -> hasSameName.test(fraudster) || fraudster.email().equals(email))
            .count();

    return new IsFraudster(count > 0);
  }
}