package com.nebula.shared.domain.fraud;

import com.nebula.shared.domain.event.DomainEvent;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.LastName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FraudCheckCreated extends DomainEvent {

    private String firstName;

    private String lastName;

    private String email;

    private boolean isFraudster;

    public FraudCheckCreated(Id aggregateId,
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