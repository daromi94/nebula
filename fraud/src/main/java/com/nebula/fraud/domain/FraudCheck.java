package com.nebula.fraud.domain;

import com.nebula.fraud.application.command.FraudCheckCreateCommand;
import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.fraud.FraudCheckCreated;
import com.nebula.shared.domain.value.*;

import java.time.LocalDateTime;

public final class FraudCheck extends AggregateRoot {

    private final Id id;

    private final FirstName firstName;

    private final LastName lastName;

    private final EmailAddress email;

    private final IsFraudster isFraudster;

    private final CreatedAt createdAt;

    public FraudCheck(Id id,
                      FirstName firstName,
                      LastName lastName,
                      EmailAddress email,
                      IsFraudster isFraudster,
                      CreatedAt createdAt) {
        this.id          = id;
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.email       = email;
        this.isFraudster = isFraudster;
        this.createdAt   = createdAt;
    }

    public static FraudCheck create(FraudCheckCreateCommand command, IsFraudster isFraudster) {
        var id        = command.id();
        var firstName = command.firstName();
        var lastName  = command.lastName();
        var email     = command.email();
        var createdAt = new CreatedAt(LocalDateTime.now());

        var fraudCheck = new FraudCheck(id, firstName, lastName, email, isFraudster, createdAt);

        fraudCheck.record(new FraudCheckCreated(id, firstName, lastName, email, isFraudster));

        return fraudCheck;
    }

    public Id id() {
        return id;
    }

    public FirstName firstName() {
        return firstName;
    }

    public LastName lastName() {
        return lastName;
    }

    public EmailAddress email() {
        return email;
    }

    public IsFraudster isFraudster() {
        return isFraudster;
    }

    public CreatedAt createdAt() {
        return createdAt;
    }

}