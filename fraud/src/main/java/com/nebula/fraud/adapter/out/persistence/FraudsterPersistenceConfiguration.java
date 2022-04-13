package com.nebula.fraud.adapter.out.persistence;

import com.nebula.fraud.adapter.out.persistence.jpa.JpaFraudsterMapper;
import com.nebula.fraud.adapter.out.persistence.jpa.JpaFraudsterPersistenceAdapter;
import com.nebula.fraud.adapter.out.persistence.jpa.JpaFraudsterRepository;
import com.nebula.fraud.application.port.out.FraudsterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
class FraudsterPersistenceConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public FraudsterRepository fraudsterRepository() {
        JpaFraudsterMapper       mapper     = new JpaFraudsterMapper();
        RepositoryFactorySupport factory    = new JpaRepositoryFactory(entityManager);
        JpaFraudsterRepository   repository = factory.getRepository(JpaFraudsterRepository.class);

        return new JpaFraudsterPersistenceAdapter(repository, mapper);
    }

}