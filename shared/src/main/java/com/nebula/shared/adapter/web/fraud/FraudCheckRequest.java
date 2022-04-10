package com.nebula.shared.adapter.web.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckRequest {

    @NotEmpty(message = "Email must not be empty!")
    @Email(message = "Email should be valid!")
    private String email;

}