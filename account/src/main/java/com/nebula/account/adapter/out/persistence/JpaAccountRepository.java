package com.nebula.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<JpaAccount, String> {

}