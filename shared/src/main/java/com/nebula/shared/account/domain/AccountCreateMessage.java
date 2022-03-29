package com.nebula.shared.account.domain;

import java.util.UUID;

public record AccountCreateMessage(UUID id, UUID customerId, double balance) {

}