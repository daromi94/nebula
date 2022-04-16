package com.nebula.account.application.command;

import com.nebula.shared.domain.commons.value.Id;

public record AccountCreateCommand(Id id, Id customerId) {

}