package com.nebula.shared.domain.fraud;

import com.nebula.shared.domain.commons.DomainEvent;
import com.nebula.shared.domain.commons.value.*;
import com.nebula.shared.domain.value.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class FraudCheckCreatedEvent extends DomainEvent {

    private String firstName;

    private String lastName;

    private String email;

    private boolean isFraudster;

    public FraudCheckCreatedEvent(Id aggregateId,
                                  FirstName firstName,
                                  LastName lastName,
                                  EmailAddress email,
                                  IsFraudster isFraudster) {
        super(aggregateId.value());

        this.firstName   = firstName.value();
        this.lastName    = lastName.value();
        this.email       = email.value();
        this.isFraudster = isFraudster.value();
    }

}