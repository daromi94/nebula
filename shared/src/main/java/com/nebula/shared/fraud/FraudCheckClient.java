package com.nebula.shared.fraud;

import com.nebula.shared.fraud.domain.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraud", path = "api/v1/fraud-check")
public interface FraudCheckClient {

    @GetMapping(path = "{email}")
    FraudCheckResponse check(@PathVariable("email") String dubiousEmail);

}