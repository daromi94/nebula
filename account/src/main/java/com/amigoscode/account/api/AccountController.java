package com.amigoscode.account.api;

import com.amigoscode.account.service.AccountService;
import com.amigoscode.clients.account.dto.AccountCreationRequest;
import com.amigoscode.clients.account.dto.AccountCreationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
@Slf4j
public record AccountController(AccountService accountService) {

    @PostMapping
    public AccountCreationResponse createAccount(@RequestBody AccountCreationRequest request) {
        log.info("new account creation request for {}", request);
        return accountService.createAccount(request);
    }

}