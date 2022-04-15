package com.nebula.fraud.application.command;

import com.nebula.shared.adapter.web.fraud.FraudChecksPostRequest;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;
import org.springframework.stereotype.Component;

@Component
public class FraudCheckCreateCommandMapper {

    public FraudCheckCreateCommand fromRequest(FraudChecksPostRequest request) {
        Id           id        = Id.of(request.getId());
        FirstName    firstName = FirstName.of(request.getFirstName());
        LastName     lastName  = LastName.of(request.getLastName());
        EmailAddress email     = EmailAddress.of(request.getEmail());

        return new FraudCheckCreateCommand(id, firstName, lastName, email);
    }

}