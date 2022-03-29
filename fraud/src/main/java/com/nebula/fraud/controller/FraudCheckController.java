package com.nebula.fraud.controller;

import com.nebula.fraud.service.FraudChecker;
import com.nebula.shared.fraud.domain.FraudCheckResponse;
import com.nebula.shared.fraud.domain.FraudsterEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public final class FraudCheckController {

    private final FraudChecker checker;

    public FraudCheckController(FraudChecker checker) {
        this.checker = checker;
    }

    @GetMapping(path = "{email}")
    public FraudCheckResponse check(@PathVariable("email") String dubiousEmail) {
        log.info("new fraud check request for {}", dubiousEmail);
        FraudsterEmail email = new FraudsterEmail(dubiousEmail);
        boolean isFraudster = checker.check(email);
        return new FraudCheckResponse(isFraudster);
    }

}