package com.nebula.account.adapter.out.persistence;

import com.nebula.account.adapter.out.persistence.jpa.JpaAccountMapper;
import com.nebula.account.adapter.out.persistence.jpa.JpaAccountPersistenceAdapter;
import com.nebula.account.adapter.out.persistence.jpa.JpaAccountRepository;
import com.nebula.account.application.port.out.AccountRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

@Configuration
class AccountPersistenceConfiguration {

  @PersistenceContext private EntityManager entityManager;

  @Bean
  public AccountRepository accountRepository() {
    var mapper = new JpaAccountMapper();
    var factory = new JpaRepositoryFactory(entityManager);
    var repository = factory.getRepository(JpaAccountRepository.class);

    return new JpaAccountPersistenceAdapter(repository, mapper);
  }
}
