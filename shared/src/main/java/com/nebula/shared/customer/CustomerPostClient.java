package com.nebula.shared.customer;

import com.nebula.shared.customer.domain.CustomerPostRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "customer", path = "api/v1/customers")
public interface CustomerPostClient {

    @PostMapping
    void post(@RequestBody CustomerPostRequest request);

}