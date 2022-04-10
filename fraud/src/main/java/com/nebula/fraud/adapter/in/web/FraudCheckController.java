package com.nebula.fraud.adapter.in.web;

import com.nebula.fraud.application.service.FraudChecker;
import com.nebula.shared.adapter.web.fraud.FraudCheckRequest;
import com.nebula.shared.adapter.web.fraud.FraudCheckResponse;
import com.nebula.shared.domain.EmailAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.request-mappings.fraud-check}")
@Slf4j
final class FraudCheckController {

    private final FraudChecker checker;

    public FraudCheckController(FraudChecker checker) {
        this.checker = checker;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public FraudCheckResponse check(@RequestBody @Valid FraudCheckRequest request) {
        log.info("fraud check request for {}", request);

        EmailAddress dubiousEmail = new EmailAddress(request.getEmail());
        boolean      isFraudster  = checker.check(dubiousEmail);

        return new FraudCheckResponse(isFraudster);
    }

}