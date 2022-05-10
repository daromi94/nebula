package com.nebula.fraud.application.command;

import com.nebula.shared.adapter.web.fraud.FraudChecksPostRequest;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.LastName;
import org.springframework.stereotype.Component;

@Component
public final class FraudCheckCreateCommandMapper {

  public FraudCheckCreateCommand fromRequest(FraudChecksPostRequest request) {
    var id = Id.of(request.getId());
    var firstName = FirstName.of(request.getFirstName());
    var lastName = LastName.of(request.getLastName());
    var email = EmailAddress.of(request.getEmail());

    return new FraudCheckCreateCommand(id, firstName, lastName, email);
  }
}