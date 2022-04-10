package com.nebula.shared.adapter.web.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@FeignClient(value = "${api.clients.fraud-check.service-name}", path = "${api.clients.fraud-check.path}")
public interface FraudCheckClient {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    FraudCheckResponse check(@RequestBody @Valid FraudCheckRequest request);

}