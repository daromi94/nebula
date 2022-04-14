package com.nebula.fraud.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaFraudsterRepository extends JpaRepository<JpaFraudster, String> {

    String FIND_BY_SIMILARITY_QUERY = """
            SELECT *
            FROM fraudsters f
            WHERE
              SIMILARITY(f.first_name, :first_name) >= :threshold
            OR
              SIMILARITY(f.last_name, :last_name) >= :threshold
            OR
              SIMILARITY(f.email, :email) >= :threshold
            """;

    @Query(value = FIND_BY_SIMILARITY_QUERY, nativeQuery = true)
    List<JpaFraudster> findBySimilarity(@Param("first_name") String firstName,
                                        @Param("last_name") String lastName,
                                        @Param("email") String email,
                                        @Param("threshold") double threshold);

}