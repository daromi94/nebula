package com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck;

import com.nebula.fraud.domain.FraudCheck;
import com.nebula.shared.domain.commons.value.CreatedAt;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.IsFraudster;
import com.nebula.shared.domain.commons.value.LastName;

public class JpaFraudCheckMapper {

  public FraudCheck fromJpa(JpaFraudCheck jpaFraudCheck) {
    var id = Id.of(jpaFraudCheck.getId());
    var firstName = FirstName.of(jpaFraudCheck.getFirstName());
    var lastName = LastName.of(jpaFraudCheck.getLastName());
    var email = EmailAddress.of(jpaFraudCheck.getEmail());
    var isFraudster = IsFraudster.of(jpaFraudCheck.isFraudster());
    var createdAt = CreatedAt.of(jpaFraudCheck.getCreatedAt());

    return new FraudCheck(id, firstName, lastName, email, isFraudster, createdAt);
  }

  public JpaFraudCheck fromDomain(FraudCheck fraudCheck) {
    var id = fraudCheck.id().value();
    var firstName = fraudCheck.firstName().value();
    var lastName = fraudCheck.lastName().value();
    var email = fraudCheck.email().value();
    var isFraudster = fraudCheck.isFraudster().value();
    var createdAt = fraudCheck.createdAt().value();

    return new JpaFraudCheck(id, firstName, lastName, email, isFraudster, createdAt);
  }
}