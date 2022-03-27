package com.amigoscode.clients.fraud;

import com.amigoscode.clients.fraud.dto.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraud", path = "api/v1/fraud-check")
public interface FraudClient {

    @GetMapping(path = "{email}")
    FraudCheckResponse isFraudster(@PathVariable("email") String email);

}