package com.nebula.account.application.service;

import com.nebula.account.application.port.out.AccountRepository;
import com.nebula.account.domain.Account;
import com.nebula.account.domain.AccountAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.commons.value.Id;
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

    public void create(Id id, Id customerId) {
        var account = Account.create(id, customerId);

        repository.search(account.id()).ifPresent(entity -> {
            throw new AccountAlreadyExistsException(entity.id());
        });

        repository.save(account);
        publisher.publish(account.pull());
    }

}