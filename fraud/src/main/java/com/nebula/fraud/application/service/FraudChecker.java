package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.fraud.FraudChecked;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FraudChecker {

    private final FraudsterRepository repository;

    private final EventPublisher publisher;

    public FraudChecker(FraudsterRepository repository, EventPublisher publisher) {
        this.repository = repository;
        this.publisher  = publisher;
    }

    public boolean check(EmailAddress email) {
        List<Fraudster> fraudsters = repository.searchByEmail(email);

        publisher.publish(new FraudChecked(email.value()));

        return !fraudsters.isEmpty();
    }

}