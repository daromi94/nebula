package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.fraud.IsFraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.LastName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FraudDetector {

    private final FraudsterRepository repository;

    @Value("${domain.fraud.similarity-threshold}")
    private double similarityThreshold;

    public FraudDetector(FraudsterRepository repository) {
        this.repository = repository;
    }

    public IsFraudster detect(FirstName firstName, LastName lastName, EmailAddress email) {
        List<Fraudster> fraudsters = repository.searchBySimilarity(firstName, lastName, email, similarityThreshold);

        return new IsFraudster(!fraudsters.isEmpty());
    }

}