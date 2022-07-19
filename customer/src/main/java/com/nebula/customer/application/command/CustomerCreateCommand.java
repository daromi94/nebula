package com.nebula.customer.application.command;

import com.nebula.shared.domain.commons.value.Id;

public record CustomerCreateCommand(Id id, FirstName firstName, LastName lastName, EmailAddress email) { }
