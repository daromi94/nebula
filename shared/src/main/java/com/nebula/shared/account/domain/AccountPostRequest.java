package com.nebula.shared.account.domain;

import java.util.UUID;

public record AccountPostRequest(UUID id, UUID customerId, double balance) {

}