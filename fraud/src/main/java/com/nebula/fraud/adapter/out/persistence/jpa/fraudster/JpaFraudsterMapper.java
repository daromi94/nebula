package com.nebula.fraud.adapter.out.persistence.jpa.fraudster;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.LastName;

public class JpaFraudsterMapper {

  public Fraudster fromJpa(JpaFraudster jpaFraudster) {
    var id = Id.of(jpaFraudster.getId());
    var firstName = FirstName.of(jpaFraudster.getFirstName());
    var lastName = LastName.of(jpaFraudster.getLastName());
    var email = EmailAddress.of(jpaFraudster.getEmail());

    return new Fraudster(id, firstName, lastName, email);
  }

  public JpaFraudster fromDomain(Fraudster fraudster) {
    var id = fraudster.id().value();
    var firstName = fraudster.firstName().value();
    var lastName = fraudster.lastName().value();
    var email = fraudster.email().value();

    return new JpaFraudster(id, firstName, lastName, email);
  }
}