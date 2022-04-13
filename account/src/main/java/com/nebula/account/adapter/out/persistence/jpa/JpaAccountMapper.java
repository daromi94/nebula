package com.nebula.account.adapter.out.persistence.jpa;

import com.nebula.account.domain.Account;
import com.nebula.shared.domain.value.Balance;
import com.nebula.shared.domain.value.Id;

public class JpaAccountMapper {

    public Account fromJpa(JpaAccount jpaAccount) {
        Id      id         = new Id(jpaAccount.getId());
        Id      customerId = new Id(jpaAccount.getCustomerId());
        Balance balance    = new Balance(jpaAccount.getBalance());

        return new Account(id, customerId, balance);
    }

    public JpaAccount fromDomain(Account account) {
        String id         = account.id().value();
        String customerId = account.customerId().value();
        double balance    = account.balance().value();

        return new JpaAccount(id, customerId, balance);
    }

}