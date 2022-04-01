package com.nebula.fraud.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface JpaFraudsterRepository extends JpaRepository<JpaFraudster, String> {

    List<JpaFraudster> findByEmail(String email);

}