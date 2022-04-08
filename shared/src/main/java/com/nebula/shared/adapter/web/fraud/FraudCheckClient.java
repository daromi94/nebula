package com.nebula.shared.adapter.web.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Email;

@FeignClient(value = "fraud", path = "api/v1/fraud-check")
public interface FraudCheckClient {

    @GetMapping(path = "{email}")
    FraudCheckResponse check(@PathVariable("email") @Email String email);

}