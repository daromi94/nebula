package com.nebula.account.application.service;

import com.nebula.account.application.port.out.AccountRepository;
import com.nebula.account.domain.Account;
import com.nebula.account.domain.AccountAlreadyExists;
import com.nebula.shared.amqp.EventBus;
import com.nebula.shared.domain.account.AccountId;
import com.nebula.shared.domain.customer.CustomerId;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public final class AccountCreator {

    private final AccountRepository repository;

    private final EventBus eventBus;

    public AccountCreator(AccountRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus   = eventBus;
    }

    public void create(AccountId id, CustomerId customerId) throws AccountAlreadyExists {
        Account account = Account.create(id, customerId);

        repository.search(id).ifPresent((entity) -> {
            throw new AccountAlreadyExists(id);
        });

        repository.save(account);
        // TODO: Publish domain events to the event bus
    }

}