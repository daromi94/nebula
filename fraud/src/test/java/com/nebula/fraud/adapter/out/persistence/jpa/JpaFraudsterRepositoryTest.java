package com.nebula.fraud.adapter.out.persistence.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaFraudsterRepositoryTest {

    @Autowired
    private JpaFraudsterRepository underTest;

    private List<JpaFraudster> fraudsters;

    @BeforeEach
    void setUp() {
        JpaFraudster dave = new JpaFraudster("20", "Dave", "Richards", "dave.richards@email.com");
        JpaFraudster dan  = new JpaFraudster("99", "Dan", "Levy", "dan.levy@email.com");

        fraudsters = List.of(dave, dan);
    }

    @Test
    void givenFraudsters_whenAllAreRetrieved_thenSameFraudstersAreFound() {
        underTest.saveAll(fraudsters);

        List<JpaFraudster> found = underTest.findAll();

        assertThat(found).isEqualTo(fraudsters);
    }

    @Test
    void givenFraudsters_whenOneIsRetrievedById_thenFraudsterIsFound() {
        underTest.saveAll(fraudsters);

        Optional<JpaFraudster> found = underTest.findById("20");

        assertThat(found).isPresent();
    }

}