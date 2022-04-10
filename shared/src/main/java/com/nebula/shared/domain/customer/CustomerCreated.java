package com.nebula.shared.domain.customer;

import com.nebula.shared.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerCreated extends DomainEvent {

    private String firstName;

    private String lastName;

    private String email;

    public CustomerCreated(String aggregateId, String firstName, String lastName, String email) {
        super(aggregateId);

        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }

}