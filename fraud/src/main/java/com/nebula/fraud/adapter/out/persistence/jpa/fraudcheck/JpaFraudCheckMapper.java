package com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck;

import com.nebula.fraud.domain.FraudCheck;
import com.nebula.shared.domain.value.*;

public class JpaFraudCheckMapper {

    public FraudCheck fromJpa(JpaFraudCheck jpaFraudCheck) {
        var id          = new Id(jpaFraudCheck.getId());
        var firstName   = new FirstName(jpaFraudCheck.getFirstName());
        var lastName    = new LastName(jpaFraudCheck.getLastName());
        var email       = new EmailAddress(jpaFraudCheck.getEmail());
        var isFraudster = new IsFraudster(jpaFraudCheck.isFraudster());
        var createdAt   = new CreatedAt(jpaFraudCheck.getCreatedAt());

        return new FraudCheck(id, firstName, lastName, email, isFraudster, createdAt);
    }

    public JpaFraudCheck fromDomain(FraudCheck fraudCheck) {
        var id          = fraudCheck.id().value();
        var firstName   = fraudCheck.firstName().value();
        var lastName    = fraudCheck.lastName().value();
        var email       = fraudCheck.email().value();
        var isFraudster = fraudCheck.isFraudster().value();
        var createdAt   = fraudCheck.createdAt().value();

        return new JpaFraudCheck(id, firstName, lastName, email, isFraudster, createdAt);
    }

}