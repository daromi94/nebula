package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.LastName;
import java.util.List;
import java.util.function.Predicate;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FraudsterFinder {

  private final FraudsterRepository repository;

  public FraudsterFinder(FraudsterRepository repository) {
    this.repository = repository;
  }

  public List<Fraudster> find(FirstName firstName, LastName lastName, EmailAddress email) {
    Predicate<Fraudster> hasSameName =
        fraudster ->
            fraudster.firstName().equals(firstName) && fraudster.lastName().equals(lastName);

    return repository.search().stream()
        .filter(fraudster -> hasSameName.test(fraudster) || fraudster.email().equals(email))
        .toList();
  }
}
