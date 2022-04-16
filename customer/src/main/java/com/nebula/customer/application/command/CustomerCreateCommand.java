package com.nebula.customer.application.command;

import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.LastName;

public record CustomerCreateCommand(Id id, FirstName firstName, LastName lastName, EmailAddress email) {

}