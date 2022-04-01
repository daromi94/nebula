package com.nebula.account.adapter.out.persistence;

import com.nebula.account.domain.Account;
import com.nebula.shared.domain.account.AccountBalance;
import com.nebula.shared.domain.account.AccountId;
import com.nebula.shared.domain.customer.CustomerId;

public class JpaAccountMapper {

    public Account fromJpa(JpaAccount jpaAccount) {
        AccountId      id         = new AccountId(jpaAccount.getId());
        CustomerId     customerId = new CustomerId(jpaAccount.getCustomerId());
        AccountBalance balance    = new AccountBalance(jpaAccount.getBalance());

        return new Account(id, customerId, balance);
    }

    public JpaAccount fromDomain(Account account) {
        String id         = account.id().value();
        String customerId = account.customerId().value();
        double balance    = account.balance().value();

        return new JpaAccount(id, customerId, balance);
    }

}