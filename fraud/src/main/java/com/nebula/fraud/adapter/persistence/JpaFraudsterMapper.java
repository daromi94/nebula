package com.nebula.fraud.adapter.persistence;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.Email;
import com.nebula.shared.domain.fraud.FraudsterId;
import org.springframework.stereotype.Component;

@Component
class JpaFraudsterMapper {

    public Fraudster fromJpa(JpaFraudster jpaFraudster) {
        FraudsterId id    = new FraudsterId(jpaFraudster.getId());
        Email       email = new Email(jpaFraudster.getEmail());

        return new Fraudster(id, email);
    }

    public JpaFraudster fromDomain(Fraudster fraudster) {
        String id    = fraudster.id().value();
        String email = fraudster.email().value();

        return new JpaFraudster(id, email);
    }

}