package com.nebula.fraud.service;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.fraud.repository.FraudsterRepository;
import com.nebula.shared.fraud.domain.DubiousEmail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class FraudChecker {

    private final FraudsterRepository repository;

    public FraudChecker(FraudsterRepository repository) {
        this.repository = repository;
    }

    public boolean check(DubiousEmail email) {
        List<Fraudster> fraudsters = repository.findByEmail(email);
        return !fraudsters.isEmpty();
    }

}