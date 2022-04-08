package com.nebula.account.adapter.out.persistence;

import com.nebula.account.adapter.out.persistence.jpa.JpaAccountMapper;
import com.nebula.account.adapter.out.persistence.jpa.JpaAccountPersistenceAdapter;
import com.nebula.account.adapter.out.persistence.jpa.JpaAccountRepository;
import com.nebula.account.application.port.out.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
class AccountPersistenceConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public AccountRepository accountRepository() {
        JpaAccountMapper         mapper     = new JpaAccountMapper();
        RepositoryFactorySupport factory    = new JpaRepositoryFactory(entityManager);
        JpaAccountRepository     repository = factory.getRepository(JpaAccountRepository.class);

        return new JpaAccountPersistenceAdapter(repository, mapper);
    }

}