package com.nebula.fraud.adapter.out.persistence.jpa;

import com.nebula.fraud.application.port.out.FraudCheckRepository;
import com.nebula.fraud.domain.FraudCheck;
import com.nebula.shared.domain.value.Id;

import java.util.Optional;

public class JpaFraudCheckPersistenceAdapter implements FraudCheckRepository {

    private final JpaFraudCheckRepository repository;

    private final JpaFraudCheckMapper mapper;

    public JpaFraudCheckPersistenceAdapter(JpaFraudCheckRepository repository, JpaFraudCheckMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public void save(FraudCheck fraudCheck) {
        JpaFraudCheck jpaFraudCheck = mapper.fromDomain(fraudCheck);

        repository.saveAndFlush(jpaFraudCheck);
    }

    @Override
    public Optional<FraudCheck> search(Id id) {
        Optional<JpaFraudCheck> optional = repository.findById(id.value());

        if (optional.isEmpty()) return Optional.empty();

        JpaFraudCheck jpaFraudCheck = optional.get();
        FraudCheck    fraudCheck    = mapper.fromJpa(jpaFraudCheck);

        return Optional.of(fraudCheck);
    }

}