package com.nebula.customer.adapter.out.persistence.jpa;

import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.commons.value.Id;

import java.util.Optional;

public class JpaCustomerPersistenceAdapter implements CustomerRepository {

    private final JpaCustomerRepository repository;

    private final JpaCustomerMapper mapper;

    public JpaCustomerPersistenceAdapter(JpaCustomerRepository repository, JpaCustomerMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public void save(Customer customer) {
        var jpaCustomer = mapper.fromDomain(customer);

        repository.save(jpaCustomer);
    }

    @Override
    public Optional<Customer> search(Id id) {
        var optional = repository.findById(id.value());

        if (optional.isEmpty()) return Optional.empty();

        var jpaCustomer = optional.get();
        var customer    = mapper.fromJpa(jpaCustomer);

        return Optional.of(customer);
    }

}