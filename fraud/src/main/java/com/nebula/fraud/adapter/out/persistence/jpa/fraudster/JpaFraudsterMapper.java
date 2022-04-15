package com.nebula.fraud.adapter.out.persistence.jpa.fraudster;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;

public class JpaFraudsterMapper {

    public Fraudster fromJpa(JpaFraudster jpaFraudster) {
        var id        = new Id(jpaFraudster.getId());
        var firstName = new FirstName(jpaFraudster.getFirstName());
        var lastName  = new LastName(jpaFraudster.getLastName());
        var email     = new EmailAddress(jpaFraudster.getEmail());

        return new Fraudster(id, firstName, lastName, email);
    }

    public JpaFraudster fromDomain(Fraudster fraudster) {
        var id        = fraudster.id().value();
        var firstName = fraudster.firstName().value();
        var lastName  = fraudster.lastName().value();
        var email     = fraudster.email().value();

        return new JpaFraudster(id, firstName, lastName, email);
    }

}