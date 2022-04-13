package com.nebula.fraud.adapter.out.persistence;

import com.nebula.fraud.adapter.out.persistence.jpa.JpaFraudCheckMapper;
import com.nebula.fraud.adapter.out.persistence.jpa.JpaFraudCheckPersistenceAdapter;
import com.nebula.fraud.adapter.out.persistence.jpa.JpaFraudCheckRepository;
import com.nebula.fraud.application.port.out.FraudCheckRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
class FraudCheckPersistenceConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public FraudCheckRepository fraudCheckRepository() {
        JpaFraudCheckMapper      mapper     = new JpaFraudCheckMapper();
        RepositoryFactorySupport factory    = new JpaRepositoryFactory(entityManager);
        JpaFraudCheckRepository  repository = factory.getRepository(JpaFraudCheckRepository.class);

        return new JpaFraudCheckPersistenceAdapter(repository, mapper);
    }

}