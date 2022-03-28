package com.nebula.customer.repository;

import com.nebula.customer.domain.Customer;
import com.nebula.shared.customer.domain.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {

}