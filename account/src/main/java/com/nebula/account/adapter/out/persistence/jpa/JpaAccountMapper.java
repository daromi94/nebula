package com.nebula.account.adapter.out.persistence.jpa;

import com.nebula.account.domain.Account;
import com.nebula.account.domain.AccountStatus;
import com.nebula.shared.domain.commons.value.Balance;
import com.nebula.shared.domain.commons.value.Id;

public class JpaAccountMapper {

  public Account fromJpa(JpaAccount jpaAccount) {
    var id = Id.of(jpaAccount.getId());
    var customerId = Id.of(jpaAccount.getCustomerId());
    var balance = Balance.of(jpaAccount.getBalance());
    var status = AccountStatus.valueOf(jpaAccount.getStatus());

    return new Account(id, customerId, balance, status);
  }

  public JpaAccount fromDomain(Account account) {
    var id = account.id().value();
    var customerId = account.customerId().value();
    var balance = account.balance().value();
    var status = account.status().name();

    return new JpaAccount(id, customerId, balance, status);
  }
}
