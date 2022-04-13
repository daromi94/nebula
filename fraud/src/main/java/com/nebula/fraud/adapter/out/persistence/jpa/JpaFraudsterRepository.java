package com.nebula.fraud.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFraudsterRepository extends JpaRepository<JpaFraudster, String> {

    @Query(value = "SELECT * FROM fraudsters f WHERE SIMILARITY(f.first_name, :first_name) >= :threshold", nativeQuery = true)
    List<JpaFraudster> findBySimilarity(@Param("first_name") String firstName, @Param("threshold") double threshold);

}