package com.nebula.account.adapter.out.persistence.jpa;

import com.nebula.account.application.port.out.AccountRepository;
import com.nebula.account.domain.Account;
import com.nebula.shared.domain.commons.value.Id;

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
        var jpaAccount = mapper.fromDomain(account);

        repository.save(jpaAccount);
    }

    @Override
    public Optional<Account> search(Id id) {
        var optional = repository.findById(id.value());

        if (optional.isEmpty()) return Optional.empty();

        var jpaAccount = optional.get();
        var account    = mapper.fromJpa(jpaAccount);

        return Optional.of(account);
    }

}