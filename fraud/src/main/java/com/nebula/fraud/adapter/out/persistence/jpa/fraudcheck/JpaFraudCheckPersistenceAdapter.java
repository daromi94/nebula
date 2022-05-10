package com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck;

import com.nebula.fraud.application.port.out.FraudCheckRepository;
import com.nebula.fraud.domain.FraudCheck;
import com.nebula.shared.domain.commons.value.Id;
import java.util.Optional;

public class JpaFraudCheckPersistenceAdapter implements FraudCheckRepository {

  private final JpaFraudCheckRepository repository;

  private final JpaFraudCheckMapper mapper;

  public JpaFraudCheckPersistenceAdapter(
      JpaFraudCheckRepository repository, JpaFraudCheckMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public void save(FraudCheck fraudCheck) {
    var jpaFraudCheck = mapper.fromDomain(fraudCheck);

    repository.save(jpaFraudCheck);
  }

  @Override
  public Optional<FraudCheck> search(Id id) {
    var optional = repository.findById(id.value());

    if (optional.isEmpty()) return Optional.empty();

    var jpaFraudCheck = optional.get();
    var fraudCheck = mapper.fromJpa(jpaFraudCheck);

    return Optional.of(fraudCheck);
  }
}