package com.nebula.fraud.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFraudCheckRepository extends JpaRepository<JpaFraudCheck, String> {

}