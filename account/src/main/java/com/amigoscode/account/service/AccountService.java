package com.amigoscode.account.service;

import com.amigoscode.account.domain.Account;
import com.amigoscode.account.repository.AccountRepository;
import com.amigoscode.clients.account.dto.AccountCreationRequest;
import com.amigoscode.clients.account.dto.AccountCreationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record AccountService(AccountRepository accountRepository) {

    public AccountCreationResponse createAccount(AccountCreationRequest request) {
        Account account = Account.builder()
                .customerId(request.customerId())
                .balance(request.balance())
                .createdAt(LocalDateTime.now())
                .build();
        accountRepository.saveAndFlush(account);
        return new AccountCreationResponse(account.getId());
    }

}