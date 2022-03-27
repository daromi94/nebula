package com.amigoscode.customer.domain;

public record CustomerRegistrationRequest(String customerNumber, String firstName, String lastName, String email) {

}