package com.nebula.fraud.application.service;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.value.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class FraudDetectorTest {

    private final FraudsterRepository repository = Mockito.mock(FraudsterRepository.class);

    private final FraudDetector underTest = new FraudDetector(repository);

    private Fraudster dave;

    @BeforeEach
    void setUp() {
        Id           id        = Id.of("917d15ee");
        FirstName    firstName = FirstName.of("Dave");
        LastName     lastName  = LastName.of("Richards");
        EmailAddress email     = EmailAddress.of("dave@email.com");

        dave = new Fraudster(id, firstName, lastName, email);
    }

    @Test
    void givenSameNames_whenDetectionAttempt_thenFraudsterDetected() {
        // Given
        given(repository.search()).willReturn(List.of(dave));

        // When
        EmailAddress email = EmailAddress.of("larry@email.com");

        IsFraudster isFraudster = underTest.detect(dave.firstName(), dave.lastName(), email);

        // Then
        assertThat(isFraudster).isEqualTo(IsFraudster.of(true));
        then(repository).should().search();
    }

    @Test
    void givenSameEmail_whenDetectionAttempt_thenFraudsterDetected() {
        // Given
        given(repository.search()).willReturn(List.of(dave));

        // When
        FirstName firstName = FirstName.of("Larry");
        LastName  lastName  = LastName.of("Ellison");

        IsFraudster isFraudster = underTest.detect(firstName, lastName, dave.email());

        // Then
        assertThat(isFraudster).isEqualTo(IsFraudster.of(true));
        then(repository).should().search();
    }

    @Test
    void givenLegitPII_whenDetectionAttempt_thenFraudsterNotDetected() {
        // Given
        given(repository.search()).willReturn(List.of(dave));

        // When
        FirstName    firstName = FirstName.of("Larry");
        LastName     lastName  = LastName.of("Ellison");
        EmailAddress email     = EmailAddress.of("larry@email.com");

        IsFraudster isFraudster = underTest.detect(firstName, lastName, email);

        // Then
        assertThat(isFraudster).isEqualTo(IsFraudster.of(false));
        then(repository).should().search();
    }

}