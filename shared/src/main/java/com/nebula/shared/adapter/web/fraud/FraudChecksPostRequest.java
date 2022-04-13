package com.nebula.shared.adapter.web.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudChecksPostRequest {

    @NotEmpty(message = "Id must not be empty!")
    @Size(min = 36, message = "Id must be at least 36 characters long!")
    private String id;

    @NotEmpty(message = "First name must not be empty!")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty!")
    private String lastName;

    @NotEmpty(message = "Email must not be empty!")
    @Email(message = "Email should be valid!")
    private String email;

}