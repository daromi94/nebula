package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.IsFraudster;
import com.nebula.shared.domain.value.LastName;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Predicate;

@Service
@Transactional
public class FraudDetector {

    private final FraudsterRepository repository;

    public FraudDetector(FraudsterRepository repository) {
        this.repository = repository;
    }

    public IsFraudster detect(FirstName firstName, LastName lastName, EmailAddress email) {
        Predicate<Fraudster> sameName = fraudster -> {
            var sameFirst = fraudster.firstName().equals(firstName);
            var sameLast  = fraudster.lastName().equals(lastName);

            return sameFirst && sameLast;
        };

        var fraudsters = repository.search()
                .stream()
                .filter(fraudster -> sameName.test(fraudster) || fraudster.email().equals(email))
                .toList();

        return new IsFraudster(!fraudsters.isEmpty());
    }

}