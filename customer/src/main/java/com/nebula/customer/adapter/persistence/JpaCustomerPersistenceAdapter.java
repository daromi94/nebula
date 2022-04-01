package com.nebula.customer.adapter.persistence;

import com.nebula.customer.application.port.out.CustomerRepository;
import com.nebula.customer.domain.Customer;
import com.nebula.shared.domain.customer.CustomerId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class JpaCustomerPersistenceAdapter implements CustomerRepository {

    private final JpaCustomerRepository repository;

    private final JpaCustomerMapper mapper;

    public JpaCustomerPersistenceAdapter(JpaCustomerRepository repository, JpaCustomerMapper mapper) {
        this.repository = repository;
        this.mapper     = mapper;
    }

    @Override
    public void save(Customer customer) {
        JpaCustomer jpaCustomer = mapper.fromDomain(customer);

        repository.saveAndFlush(jpaCustomer);
    }

    @Override
    public Optional<Customer> search(CustomerId id) {
        Optional<JpaCustomer> optional = repository.findById(id.value());

        if (optional.isEmpty()) return Optional.empty();

        JpaCustomer jpaCustomer = optional.get();
        Customer    customer    = mapper.fromJpa(jpaCustomer);

        return Optional.of(customer);
    }

}