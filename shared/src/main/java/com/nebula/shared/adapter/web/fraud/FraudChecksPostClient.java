package com.nebula.shared.adapter.web.fraud;

import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
    value = "${api.clients.fraud-checks-post.service-name}",
    path = "${api.clients.fraud-checks-post.path}")
public interface FraudChecksPostClient {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  FraudChecksPostResponse post(@RequestBody @Valid FraudChecksPostRequest request);
}