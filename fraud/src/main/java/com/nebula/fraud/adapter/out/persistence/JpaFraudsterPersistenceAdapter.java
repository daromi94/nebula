package com.nebula.fraud.adapter.out.persistence;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.Email;

import java.util.List;

public class JpaFraudsterPersistenceAdapter implements FraudsterRepository {

    private final JpaFraudsterRepository repository;

    private final JpaFraudsterMapper mapper;

    public JpaFraudsterPersistenceAdapter(JpaFraudsterRepository repository, JpaFraudsterMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public List<Fraudster> searchByEmail(Email email) {
        return repository.findByEmail(email.value()).stream().map(mapper::fromJpa).toList();
    }

}