package com.nebula.fraud.application.service;

import com.nebula.fraud.application.command.FraudCheckCreateCommand;
import com.nebula.fraud.application.port.out.FraudCheckRepository;
import com.nebula.fraud.domain.FraudCheck;
import com.nebula.fraud.domain.FraudCheckAlreadyExistsException;
import com.nebula.shared.application.service.EventPublisher;
import com.nebula.shared.domain.DomainEvent;
import com.nebula.shared.domain.value.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class FraudCheckCreatorTest {

    private FraudCheckCreator underTest;

    @Mock
    private FraudCheckRepository repository;

    @Mock
    private FraudDetector detector;

    @Mock
    private EventPublisher publisher;

    @Captor
    private ArgumentCaptor<List<DomainEvent>> eventsCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new FraudCheckCreator(repository, detector, publisher);
    }

    @Test
    void givenNotTakenId_whenCreationAttempt_thenCreationSucceeds() {
        // Given
        Id           id          = Id.of("917d15ee");
        FirstName    firstName   = FirstName.of("Dave");
        LastName     lastName    = LastName.of("Richards");
        EmailAddress email       = EmailAddress.of("dave@email.com");
        IsFraudster  isFraudster = IsFraudster.of(false);

        given(repository.search(id)).willReturn(Optional.empty());
        given(detector.detect(firstName, lastName, email)).willReturn(isFraudster);

        // When-Then
        FraudCheckCreateCommand command = new FraudCheckCreateCommand(id, firstName, lastName, email);

        assertThat(underTest.create(command)).isEqualTo(isFraudster);

        // Then
        then(detector).should().detect(firstName, lastName, email);
        then(repository).should().search(id);
        then(repository).should().save(any());
        then(publisher).should().publish(eventsCaptor.capture());

        List<DomainEvent> events = eventsCaptor.getValue();
        events.forEach(event -> assertThat(event.getAggregateId()).isEqualTo(id.value()));
    }

    @Test
    void givenTakenId_whenCreationAttempt_thenCreationFails() {
        // Given
        Id           id          = Id.of("9a544bec");
        FirstName    firstName   = FirstName.of("Larry");
        LastName     lastName    = LastName.of("Ellison");
        EmailAddress email       = EmailAddress.of("larry@email.com");
        IsFraudster  isFraudster = IsFraudster.of(false);
        CreatedAt    createdAt   = CreatedAt.of(LocalDateTime.now().minusMonths(1));

        FraudCheck fraudCheck = new FraudCheck(id, firstName, lastName, email, isFraudster, createdAt);

        given(repository.search(id)).willReturn(Optional.of(fraudCheck));
        given(detector.detect(firstName, lastName, email)).willReturn(isFraudster);

        // When-Then
        FraudCheckCreateCommand command = new FraudCheckCreateCommand(id, firstName, lastName, email);

        assertThatThrownBy(() -> underTest.create(command)).isInstanceOf(FraudCheckAlreadyExistsException.class);

        // Then
        then(detector).should().detect(firstName, lastName, email);
        then(repository).should().search(id);
        then(repository).should(never()).save(any());
        then(publisher).should(never()).publish(anyList());
    }

}