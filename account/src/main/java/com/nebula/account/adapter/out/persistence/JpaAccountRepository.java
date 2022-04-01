package com.nebula.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaAccountRepository extends JpaRepository<JpaAccount, String> {

}