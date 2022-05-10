package com.nebula.account.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<JpaAccount, String> {}
