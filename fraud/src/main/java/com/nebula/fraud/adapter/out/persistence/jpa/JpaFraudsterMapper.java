package com.nebula.fraud.adapter.out.persistence.jpa;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.fraud.FraudsterId;

public class JpaFraudsterMapper {

    public Fraudster fromJpa(JpaFraudster jpaFraudster) {
        FraudsterId  id    = new FraudsterId(jpaFraudster.getId());
        EmailAddress email = new EmailAddress(jpaFraudster.getEmail());

        return new Fraudster(id, email);
    }

    public JpaFraudster fromDomain(Fraudster fraudster) {
        String id    = fraudster.id().value();
        String email = fraudster.email().value();

        return new JpaFraudster(id, email);
    }

}