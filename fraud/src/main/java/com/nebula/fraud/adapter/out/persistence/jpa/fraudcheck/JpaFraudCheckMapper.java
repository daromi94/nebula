package com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck;

import com.nebula.fraud.domain.FraudCheck;
import com.nebula.shared.domain.value.*;

import java.time.LocalDateTime;

public class JpaFraudCheckMapper {

    public FraudCheck fromJpa(JpaFraudCheck jpaFraudCheck) {
        Id           id          = new Id(jpaFraudCheck.getId());
        FirstName    firstName   = new FirstName(jpaFraudCheck.getFirstName());
        LastName     lastName    = new LastName(jpaFraudCheck.getLastName());
        EmailAddress email       = new EmailAddress(jpaFraudCheck.getEmail());
        IsFraudster  isFraudster = new IsFraudster(jpaFraudCheck.isFraudster());
        CreatedAt    createdAt   = new CreatedAt(jpaFraudCheck.getCreatedAt());

        return new FraudCheck(id, firstName, lastName, email, isFraudster, createdAt);
    }

    public JpaFraudCheck fromDomain(FraudCheck fraudCheck) {
        String        id          = fraudCheck.id().value();
        String        firstName   = fraudCheck.firstName().value();
        String        lastName    = fraudCheck.lastName().value();
        String        email       = fraudCheck.email().value();
        boolean       isFraudster = fraudCheck.isFraudster().value();
        LocalDateTime createdAt   = fraudCheck.createdAt().value();

        return new JpaFraudCheck(id, firstName, lastName, email, isFraudster, createdAt);
    }

}