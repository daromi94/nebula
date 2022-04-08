package com.nebula.fraud.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFraudsterRepository extends JpaRepository<JpaFraudster, String> {

    List<JpaFraudster> findByEmail(String email);

}