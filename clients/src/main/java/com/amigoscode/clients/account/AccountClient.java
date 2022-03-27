package com.amigoscode.clients.account;

import com.amigoscode.clients.account.dto.AccountCreationRequest;
import com.amigoscode.clients.account.dto.AccountCreationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "account", path = "api/v1/accounts")
public interface AccountClient {

    @PostMapping
    AccountCreationResponse createAccount(@RequestBody AccountCreationRequest request);

}