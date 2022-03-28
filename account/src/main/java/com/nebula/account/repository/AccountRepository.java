package com.nebula.account.repository;

import com.nebula.account.domain.Account;
import com.nebula.shared.account.domain.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, AccountId> {

}