package com.amigoscode.fraud.api;

import com.amigoscode.clients.fraud.dto.FraudCheckResponse;
import com.amigoscode.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{email}")
    public FraudCheckResponse isFraudster(@PathVariable("email") String email) {
        log.info("new fraud check request for {}", email);
        return fraudCheckService.isFraudulentCustomer(email);
    }

}