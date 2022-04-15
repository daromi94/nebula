package com.nebula.fraud.application.command;

import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;

public record FraudCheckCreateCommand(Id id, FirstName firstName, LastName lastName, EmailAddress email) {

}