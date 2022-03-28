package com.nebula.shared.account;

import com.nebula.shared.account.domain.AccountPostRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "account", path = "api/v1/accounts")
public interface AccountPostClient {

    @PostMapping
    void post(@RequestBody AccountPostRequest request);

}