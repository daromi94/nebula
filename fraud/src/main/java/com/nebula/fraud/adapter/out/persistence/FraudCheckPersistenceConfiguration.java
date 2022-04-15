package com.nebula.fraud.adapter.out.persistence;

import com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck.JpaFraudCheckMapper;
import com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck.JpaFraudCheckPersistenceAdapter;
import com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck.JpaFraudCheckRepository;
import com.nebula.fraud.application.port.out.FraudCheckRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
class FraudCheckPersistenceConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public FraudCheckRepository fraudCheckRepository() {
        var mapper     = new JpaFraudCheckMapper();
        var factory    = new JpaRepositoryFactory(entityManager);
        var repository = factory.getRepository(JpaFraudCheckRepository.class);

        return new JpaFraudCheckPersistenceAdapter(repository, mapper);
    }

}