package com.amigoscode.clients.customer.dto;

import java.util.UUID;

public record CustomerRegistrationResponse(UUID customerId, UUID accountId) {

}