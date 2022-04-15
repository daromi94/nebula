package com.nebula.shared.domain.account;

import com.nebula.shared.domain.DomainEvent;
import com.nebula.shared.domain.value.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class AccountCreatedEvent extends DomainEvent {

    private String customerId;

    public AccountCreatedEvent(Id aggregateId, Id customerId) {
        super(aggregateId.value());

        this.customerId = customerId.value();
    }

}