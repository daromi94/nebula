package com.nebula.customer.adapter.out.persistence;

import com.nebula.customer.adapter.out.persistence.jpa.JpaCustomerMapper;
import com.nebula.customer.adapter.out.persistence.jpa.JpaCustomerPersistenceAdapter;
import com.nebula.customer.adapter.out.persistence.jpa.JpaCustomerRepository;
import com.nebula.customer.application.port.out.CustomerRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

@Configuration
class CustomerPersistenceConfiguration {

  @PersistenceContext private EntityManager entityManager;

  @Bean
  public CustomerRepository customerRepository() {
    var mapper = new JpaCustomerMapper();
    var factory = new JpaRepositoryFactory(entityManager);
    var repository = factory.getRepository(JpaCustomerRepository.class);

    return new JpaCustomerPersistenceAdapter(repository, mapper);
  }
}
