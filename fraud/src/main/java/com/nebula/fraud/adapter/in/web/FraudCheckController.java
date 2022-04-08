package com.nebula.fraud.adapter.in.web;

import com.nebula.fraud.application.service.FraudChecker;
import com.nebula.shared.adapter.web.fraud.FraudCheckResponse;
import com.nebula.shared.domain.EmailAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
final class FraudCheckController {

    private final FraudChecker checker;

    public FraudCheckController(FraudChecker checker) {
        this.checker = checker;
    }

    @GetMapping(path = "{email}")
    public FraudCheckResponse check(@PathVariable("email") @Email String email) {
        log.info("new fraud check request for {}", email);

        EmailAddress dubiousEmail = new EmailAddress(email);
        boolean      isFraudster  = checker.check(dubiousEmail);

        return new FraudCheckResponse(isFraudster);
    }

}