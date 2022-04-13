package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudCheckRepository;
import com.nebula.fraud.domain.FraudCheck;
import com.nebula.fraud.domain.FraudCheckAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.fraud.IsFraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FraudCheckCreator {

    private final FraudCheckRepository repository;

    private final EventPublisher publisher;

    public FraudCheckCreator(FraudCheckRepository repository, EventPublisher publisher) {
        this.repository = repository;
        this.publisher  = publisher;
    }

    public void create(Id id,
                       FirstName firstName,
                       LastName lastName,
                       EmailAddress email,
                       IsFraudster isFraudster) throws FraudCheckAlreadyExistsException {
        FraudCheck fraudCheck = FraudCheck.create(id, firstName, lastName, email, isFraudster);

        repository.search(id).ifPresent(entity -> {
            throw new FraudCheckAlreadyExistsException(id);
        });

        repository.save(fraudCheck);
        publisher.publish(fraudCheck.pull());
    }

}