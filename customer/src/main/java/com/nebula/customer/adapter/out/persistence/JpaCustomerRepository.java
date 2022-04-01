package com.nebula.customer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<JpaCustomer, String> {

}