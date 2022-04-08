package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.EmailAddress;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FraudChecker {

    private final FraudsterRepository repository;

    public FraudChecker(FraudsterRepository repository) {
        this.repository = repository;
    }

    public boolean check(EmailAddress email) {
        List<Fraudster> fraudsters = repository.searchByEmail(email);

        return !fraudsters.isEmpty();
    }

}