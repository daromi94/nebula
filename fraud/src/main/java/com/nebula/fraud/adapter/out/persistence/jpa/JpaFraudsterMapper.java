package com.nebula.fraud.adapter.out.persistence.jpa;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;

public class JpaFraudsterMapper {

    public Fraudster fromJpa(JpaFraudster jpaFraudster) {
        Id           id        = new Id(jpaFraudster.getId());
        FirstName    firstName = new FirstName(jpaFraudster.getFirstName());
        LastName     lastName  = new LastName(jpaFraudster.getLastName());
        EmailAddress email     = new EmailAddress(jpaFraudster.getEmail());

        return new Fraudster(id, firstName, lastName, email);
    }

    public JpaFraudster fromDomain(Fraudster fraudster) {
        String id        = fraudster.id().value();
        String firstName = fraudster.firstName().value();
        String lastName  = fraudster.lastName().value();
        String email     = fraudster.email().value();

        return new JpaFraudster(id, firstName, lastName, email);
    }

}