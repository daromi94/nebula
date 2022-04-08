package com.nebula.account.application.service;

import com.nebula.account.application.port.out.AccountRepository;
import com.nebula.account.domain.Account;
import com.nebula.account.domain.AccountAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.account.AccountId;
import com.nebula.shared.domain.customer.CustomerId;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountCreator {

    private final AccountRepository repository;

    private final EventPublisher publisher;

    public AccountCreator(AccountRepository repository, EventPublisher publisher) {
        this.repository = repository;
        this.publisher  = publisher;
    }

    public void create(AccountId id, CustomerId customerId) throws AccountAlreadyExistsException {
        Account account = Account.create(id, customerId);

        repository.search(id).ifPresent((entity) -> {
            throw new AccountAlreadyExistsException(id);
        });

        repository.save(account);
        // TODO: Publish domain events
    }

}