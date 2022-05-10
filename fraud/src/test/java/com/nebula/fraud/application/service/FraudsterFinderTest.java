package com.nebula.fraud.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.openMocks;

import com.nebula.fraud.application.port.out.FraudsterRepository;
import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.LastName;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class FraudsterFinderTest {

  private FraudsterFinder underTest;

  @Mock private FraudsterRepository repository;

  private Fraudster dave;

  private Fraudster larry;

  @BeforeEach
  void setUp() {
    openMocks(this);

    underTest = new FraudsterFinder(repository);

    dave =
        new Fraudster(
            Id.of("917d15ee"),
            FirstName.of("Dave"),
            LastName.of("Richards"),
            EmailAddress.of("dave@email.com"));

    larry =
        new Fraudster(
            Id.of("816y15k7"),
            FirstName.of("Larry"),
            LastName.of("Ellison"),
            EmailAddress.of("larry@email.com"));
  }

  @Test
  void givenNoReportedFraudsters_whenFindingAttempt_thenNoFraudstersAreFound() {
    // Given
    given(repository.search()).willReturn(Collections.emptyList());

    // When
    var fraudsters = underTest.find(larry.firstName(), larry.lastName(), larry.email());

    // Then
    assertThat(fraudsters).isEmpty();
    then(repository).should().search();
  }

  @Test
  void givenSameEmailOfReportedFraudster_whenFindingAttempt_thenFraudsterIsFound() {
    // Given
    given(repository.search()).willReturn(List.of(dave));

    // When
    var fraudsters = underTest.find(larry.firstName(), larry.lastName(), dave.email());

    // Then
    assertThat(fraudsters.stream().anyMatch(fraudster -> fraudster.id().equals(dave.id())))
        .isTrue();
    then(repository).should().search();
  }

  @Test
  void givenSameNamesOfReportedFraudster_whenFindingAttempt_thenFraudsterIsFound() {
    // Given
    given(repository.search()).willReturn(List.of(dave));

    // When
    var fraudsters = underTest.find(dave.firstName(), dave.lastName(), larry.email());

    // Then
    assertThat(fraudsters.stream().anyMatch(fraudster -> fraudster.id().equals(dave.id())))
        .isTrue();
    then(repository).should().search();
  }

  @Test
  void givenLawAbidingCitizen_whenFindingAttempt_thenNoFraudstersAreFound() {
    // Given
    given(repository.search()).willReturn(List.of(dave));

    // When
    var fraudsters = underTest.find(larry.firstName(), larry.lastName(), larry.email());

    // Then
    assertThat(fraudsters).isEmpty();
    then(repository).should().search();
  }
}
