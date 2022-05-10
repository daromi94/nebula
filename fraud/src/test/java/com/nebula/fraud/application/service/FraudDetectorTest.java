package com.nebula.fraud.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.IsFraudster;
import com.nebula.shared.domain.commons.value.LastName;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FraudDetectorTest {

  private FraudDetector underTest;

  @Mock private FraudsterRepository repository;

  private Fraudster dave;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    underTest = new FraudDetector(repository);

    var id = Id.of("917d15ee");
    var firstName = FirstName.of("Dave");
    var lastName = LastName.of("Richards");
    var email = EmailAddress.of("dave@email.com");

    dave = new Fraudster(id, firstName, lastName, email);
  }

  @Test
  void givenNoReportedFraudsters_whenDetectionAttempt_thenFraudsterNotDetected() {
    // Given
    given(repository.search()).willReturn(Collections.emptyList());

    // When
    var firstName = FirstName.of("Larry");
    var lastName = LastName.of("Ellison");
    var email = EmailAddress.of("larry@email.com");

    var isFraudster = underTest.detect(firstName, lastName, email);

    // Then
    assertThat(isFraudster).isEqualTo(IsFraudster.of(false));
    then(repository).should().search();
  }

  @Test
  void givenSameEmailOfReportedFraudster_whenDetectionAttempt_thenFraudsterDetected() {
    // Given
    given(repository.search()).willReturn(List.of(dave));

    // When
    var firstName = FirstName.of("Larry");
    var lastName = LastName.of("Ellison");

    var isFraudster = underTest.detect(firstName, lastName, dave.email());

    // Then
    assertThat(isFraudster).isEqualTo(IsFraudster.of(true));
    then(repository).should().search();
  }

  @Test
  void givenSameNamesOfReportedFraudster_whenDetectionAttempt_thenFraudsterDetected() {
    // Given
    given(repository.search()).willReturn(List.of(dave));

    // When
    var email = EmailAddress.of("larry@email.com");

    var isFraudster = underTest.detect(dave.firstName(), dave.lastName(), email);

    // Then
    assertThat(isFraudster).isEqualTo(IsFraudster.of(true));
    then(repository).should().search();
  }

  @Test
  void givenLawAbidingCitizen_whenDetectionAttempt_thenFraudsterNotDetected() {
    // Given
    given(repository.search()).willReturn(List.of(dave));

    // When
    var firstName = FirstName.of("Larry");
    var lastName = LastName.of("Ellison");
    var email = EmailAddress.of("larry@email.com");

    var isFraudster = underTest.detect(firstName, lastName, email);

    // Then
    assertThat(isFraudster).isEqualTo(IsFraudster.of(false));
    then(repository).should().search();
  }
}