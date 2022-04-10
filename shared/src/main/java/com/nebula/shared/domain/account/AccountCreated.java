package com.nebula.shared.domain.account;

import com.nebula.shared.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountCreated extends DomainEvent {

    private String customerId;

    public AccountCreated(String aggregateId, String customerId) {
        super(aggregateId);

        this.customerId = customerId;
    }

}