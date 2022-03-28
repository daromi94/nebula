package com.nebula.account.service;

import com.nebula.account.domain.Account;
import com.nebula.account.repository.AccountRepository;
import com.nebula.shared.account.domain.AccountBalance;
import com.nebula.shared.account.domain.AccountId;
import com.nebula.shared.customer.domain.CustomerId;
import org.springframework.stereotype.Service;

@Service
public final class AccountCreator {

    private final AccountRepository repository;

    public AccountCreator(AccountRepository repository) {
        this.repository = repository;
    }

    public void create(AccountId id, CustomerId customerId, AccountBalance balance) {
        Account account = new Account(id, customerId, balance);
        if (repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("account already exists!");
        }
        repository.save(account);
    }

}