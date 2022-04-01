package com.nebula.customer.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaCustomerRepository extends JpaRepository<JpaCustomer, String> {

}