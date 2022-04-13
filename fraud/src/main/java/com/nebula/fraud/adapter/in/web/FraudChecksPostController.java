package com.nebula.fraud.adapter.in.web;

import com.nebula.fraud.application.service.FraudCheckCreator;
import com.nebula.fraud.application.service.FraudDetector;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostRequest;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostResponse;
import com.nebula.shared.domain.fraud.IsFraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.request-mappings.fraud-checks-post}")
@Slf4j
final class FraudChecksPostController {

    private final FraudDetector detector;

    private final FraudCheckCreator creator;

    public FraudChecksPostController(FraudDetector detector, FraudCheckCreator creator) {
        this.detector = detector;
        this.creator  = creator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FraudChecksPostResponse post(@RequestBody @Valid FraudChecksPostRequest request) {
        log.info("fraud checks post request for {}", request);

        Id           id        = new Id(request.getId());
        FirstName    firstName = new FirstName(request.getFirstName());
        LastName     lastName  = new LastName(request.getLastName());
        EmailAddress email     = new EmailAddress(request.getEmail());

        IsFraudster isFraudster = detector.detect(firstName, lastName, email);
        creator.create(id, firstName, lastName, email, isFraudster);

        return new FraudChecksPostResponse(isFraudster.value());
    }

}