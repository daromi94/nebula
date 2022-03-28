package com.nebula.shared.customer.domain;

import java.util.UUID;

public record CustomerPostRequest(UUID id, String firstName, String lastName, String email) {

}