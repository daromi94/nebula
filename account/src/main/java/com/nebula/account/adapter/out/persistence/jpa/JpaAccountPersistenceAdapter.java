package com.nebula.account.adapter.out.persistence.jpa;

import com.nebula.account.application.port.out.AccountRepository;
import com.nebula.account.domain.Account;
import com.nebula.shared.domain.value.Id;

import java.util.Optional;

public class JpaAccountPersistenceAdapter implements AccountRepository {

    private final JpaAccountRepository repository;

    private final JpaAccountMapper mapper;

    public JpaAccountPersistenceAdapter(JpaAccountRepository repository, JpaAccountMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public void save(Account account) {
        JpaAccount jpaAccount = mapper.fromDomain(account);

        repository.saveAndFlush(jpaAccount);
    }

    @Override
    public Optional<Account> search(Id id) {
        Optional<JpaAccount> optional = repository.findById(id.value());

        if (optional.isEmpty()) return Optional.empty();

        JpaAccount jpaAccount = optional.get();
        Account    account    = mapper.fromJpa(jpaAccount);

        return Optional.of(account);
    }

}