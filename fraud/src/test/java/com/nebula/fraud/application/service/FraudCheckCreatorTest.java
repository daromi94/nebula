package com.nebula.fraud.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

import com.nebula.fraud.application.command.FraudCheckCreateCommand;
import com.nebula.fraud.application.port.out.FraudCheckRepository;
import com.nebula.fraud.domain.FraudCheck;
import com.nebula.fraud.domain.FraudCheckAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.commons.DomainEvent;
import com.nebula.shared.domain.commons.value.CreatedAt;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.IsFraudster;
import com.nebula.shared.domain.commons.value.LastName;
import com.nebula.shared.util.CustomRuntimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FraudCheckCreatorTest {

  private FraudCheckCreator underTest;

  @Mock private FraudCheckRepository repository;

  @Mock private FraudDetector detector;

  @Mock private EventPublisher publisher;

  @Captor private ArgumentCaptor<List<DomainEvent>> eventsCaptor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    underTest = new FraudCheckCreator(repository, detector, publisher);
  }

  @Test
  void givenDetectionFailsDueToRuntimeException_whenCreationAttempt_thenCreationFails() {
    // Given
    var id = Id.of("917d15ee");
    var firstName = FirstName.of("Dave");
    var lastName = LastName.of("Richards");
    var email = EmailAddress.of("dave@email.com");

    given(detector.detect(firstName, lastName, email)).willThrow(CustomRuntimeException.class);

    // When-Then
    var command = new FraudCheckCreateCommand(id, firstName, lastName, email);

    assertThatThrownBy(() -> underTest.create(command)).isInstanceOf(CustomRuntimeException.class);

    // Then
    then(detector).should().detect(firstName, lastName, email);
    then(repository).should(never()).search(id);
    then(repository).should(never()).save(any());
    then(publisher).should(never()).publish(anyList());
  }

  @Test
  void givenTakenId_whenCreationAttempt_thenCreationFails() {
    // Given
    var id = Id.of("9a544bec");
    var firstName = FirstName.of("Larry");
    var lastName = LastName.of("Ellison");
    var email = EmailAddress.of("larry@email.com");
    var isFraudster = IsFraudster.of(false);
    var createdAt = CreatedAt.of(LocalDateTime.now().minusMonths(1));

    var fraudCheck = new FraudCheck(id, firstName, lastName, email, isFraudster, createdAt);

    given(detector.detect(firstName, lastName, email)).willReturn(isFraudster);
    given(repository.search(id)).willReturn(Optional.of(fraudCheck));

    // When-Then
    var command = new FraudCheckCreateCommand(id, firstName, lastName, email);

    assertThatThrownBy(() -> underTest.create(command))
        .isInstanceOf(FraudCheckAlreadyExistsException.class);

    // Then
    then(detector).should().detect(firstName, lastName, email);
    then(repository).should().search(id);
    then(repository).should(never()).save(any());
    then(publisher).should(never()).publish(anyList());
  }

  @Test
  void givenEventPublishingFailsDueToRuntimeException_whenCreationAttempt_thenCreationFails() {
    // Given
    var id = Id.of("917d15ee");
    var firstName = FirstName.of("Dave");
    var lastName = LastName.of("Richards");
    var email = EmailAddress.of("dave@email.com");
    var isFraudster = IsFraudster.of(false);

    given(detector.detect(firstName, lastName, email)).willReturn(isFraudster);
    given(repository.search(id)).willReturn(Optional.empty());
    doThrow(CustomRuntimeException.class).when(publisher).publish(anyList());

    // When-Then
    var command = new FraudCheckCreateCommand(id, firstName, lastName, email);

    assertThatThrownBy(() -> underTest.create(command)).isInstanceOf(CustomRuntimeException.class);

    // Then
    then(detector).should().detect(firstName, lastName, email);
    then(repository).should().search(id);
    then(repository).should().save(any());
    then(publisher).should().publish(anyList());
  }

  @Test
  void givenErrorlessFraudCheck_whenCreationAttempt_thenCreationSucceeds() {
    // Given
    var id = Id.of("917d15ee");
    var firstName = FirstName.of("Dave");
    var lastName = LastName.of("Richards");
    var email = EmailAddress.of("dave@email.com");
    var isFraudster = IsFraudster.of(false);

    given(detector.detect(firstName, lastName, email)).willReturn(isFraudster);
    given(repository.search(id)).willReturn(Optional.empty());

    // When-Then
    var command = new FraudCheckCreateCommand(id, firstName, lastName, email);

    assertThat(underTest.create(command)).isEqualTo(isFraudster);

    // Then
    then(detector).should().detect(firstName, lastName, email);
    then(repository).should().search(id);
    then(repository).should().save(any());
    then(publisher).should().publish(eventsCaptor.capture());

    var events = eventsCaptor.getValue();
    events.forEach(event -> assertThat(event.getAggregateId()).isEqualTo(id.value()));
  }
}