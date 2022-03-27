package com.amigoscode.customer.api;

public record CustomerRegistrationRequest(String customerNumber, String firstName, String lastName, String email) {

}