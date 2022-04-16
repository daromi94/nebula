package com.nebula.fraud.application.service;

import com.nebula.fraud.application.command.FraudCheckCreateCommand;
import com.nebula.fraud.application.port.out.FraudCheckRepository;
import com.nebula.fraud.domain.FraudCheck;
import com.nebula.fraud.domain.FraudCheckAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.commons.value.IsFraudster;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FraudCheckCreator {

    private final FraudCheckRepository repository;

    private final FraudDetector detector;

    private final EventPublisher publisher;

    public FraudCheckCreator(FraudCheckRepository repository, FraudDetector detector, EventPublisher publisher) {
        this.repository = repository;
        this.detector   = detector;
        this.publisher  = publisher;
    }

    public IsFraudster create(FraudCheckCreateCommand command) {
        var isFraudster = detector.detect(command.firstName(), command.lastName(), command.email());
        var fraudCheck  = FraudCheck.create(command, isFraudster);

        repository.search(fraudCheck.id()).ifPresent(entity -> {
            throw new FraudCheckAlreadyExistsException(entity.id());
        });

        repository.save(fraudCheck);
        publisher.publish(fraudCheck.pull());

        return isFraudster;
    }

}