package com.nebula.customer.adapter.out.persistence;

import com.nebula.customer.adapter.out.persistence.jpa.JpaCustomerMapper;
import com.nebula.customer.adapter.out.persistence.jpa.JpaCustomerPersistenceAdapter;
import com.nebula.customer.adapter.out.persistence.jpa.JpaCustomerRepository;
import com.nebula.customer.application.port.out.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
class CustomerPersistenceConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public CustomerRepository customerRepository() {
        JpaCustomerMapper        mapper     = new JpaCustomerMapper();
        RepositoryFactorySupport factory    = new JpaRepositoryFactory(entityManager);
        JpaCustomerRepository    repository = factory.getRepository(JpaCustomerRepository.class);

        return new JpaCustomerPersistenceAdapter(repository, mapper);
    }

}