package com.nebula.account.application.command;

import com.nebula.shared.domain.value.Id;

public record AccountCreateCommand(Id id, Id customerId) {

}