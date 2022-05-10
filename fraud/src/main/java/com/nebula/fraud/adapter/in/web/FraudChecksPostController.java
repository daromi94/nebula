package com.nebula.fraud.adapter.in.web;

import com.nebula.fraud.application.command.FraudCheckCreateCommandMapper;
import com.nebula.fraud.application.service.FraudCheckCreator;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostRequest;
import com.nebula.shared.adapter.web.fraud.FraudChecksPostResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.request-mappings.fraud-checks-post}")
@Slf4j
final class FraudChecksPostController {

  private final FraudCheckCreator creator;

  private final FraudCheckCreateCommandMapper mapper;

  public FraudChecksPostController(
      FraudCheckCreator creator, FraudCheckCreateCommandMapper mapper) {
    this.creator = creator;
    this.mapper = mapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FraudChecksPostResponse post(@RequestBody @Valid FraudChecksPostRequest request) {
    log.info("fraud checks post request for {}", request);

    var command = mapper.fromRequest(request);

    var isFraudster = creator.create(command);

    return new FraudChecksPostResponse(isFraudster.value());
  }
}