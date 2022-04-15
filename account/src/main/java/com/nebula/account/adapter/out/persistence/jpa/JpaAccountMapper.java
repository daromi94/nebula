package com.nebula.account.adapter.out.persistence.jpa;

import com.nebula.account.domain.Account;
import com.nebula.shared.domain.value.Balance;
import com.nebula.shared.domain.value.Id;

public class JpaAccountMapper {

    public Account fromJpa(JpaAccount jpaAccount) {
        var id         = new Id(jpaAccount.getId());
        var customerId = new Id(jpaAccount.getCustomerId());
        var balance    = new Balance(jpaAccount.getBalance());

        return new Account(id, customerId, balance);
    }

    public JpaAccount fromDomain(Account account) {
        var id         = account.id().value();
        var customerId = account.customerId().value();
        var balance    = account.balance().value();

        return new JpaAccount(id, customerId, balance);
    }

}