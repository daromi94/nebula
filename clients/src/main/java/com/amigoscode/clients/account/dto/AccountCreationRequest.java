package com.amigoscode.clients.account.dto;

import java.util.UUID;

public record AccountCreationRequest(UUID customerId, double balance) {

}