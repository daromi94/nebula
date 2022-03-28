package com.nebula.account.controller;

import com.nebula.account.service.AccountCreator;
import com.nebula.shared.account.domain.AccountBalance;
import com.nebula.shared.account.domain.AccountId;
import com.nebula.shared.account.domain.AccountPostRequest;
import com.nebula.shared.customer.domain.CustomerId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
@Slf4j
public final class AccountPostController {

    private final AccountCreator creator;

    public AccountPostController(AccountCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public void post(@RequestBody AccountPostRequest request) {
        log.info("new account post request for {}", request);
        AccountId id = new AccountId(request.id());
        CustomerId customerId = new CustomerId(request.customerId());
        AccountBalance balance = new AccountBalance(request.balance());
        creator.create(id, customerId, balance);
    }

}