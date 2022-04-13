package com.nebula.fraud.adapter.out.persistence.jpa;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.LastName;

import java.util.List;

public class JpaFraudsterPersistenceAdapter implements FraudsterRepository {

    private final JpaFraudsterRepository repository;

    private final JpaFraudsterMapper mapper;

    public JpaFraudsterPersistenceAdapter(JpaFraudsterRepository repository, JpaFraudsterMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public List<Fraudster> searchBySimilarity(FirstName firstName,
                                              LastName lastName,
                                              EmailAddress email,
                                              double threshold) {
        return repository.findBySimilarity(firstName.value(), threshold).stream().map(mapper::fromJpa).toList();
    }

}