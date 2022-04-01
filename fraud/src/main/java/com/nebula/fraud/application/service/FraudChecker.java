package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.Email;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class FraudChecker {

    private final FraudsterRepository repository;

    public FraudChecker(FraudsterRepository repository) {
        this.repository = repository;
    }

    public boolean check(Email email) {
        List<Fraudster> fraudsters = repository.searchByEmail(email);

        return !fraudsters.isEmpty();
    }

}