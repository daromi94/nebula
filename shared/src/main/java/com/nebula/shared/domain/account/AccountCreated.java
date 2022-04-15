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
public final class AccountCreated extends DomainEvent {

    private String customerId;

    public AccountCreated(Id aggregateId, Id customerId) {
        super(aggregateId.value());

        this.customerId = customerId.value();
    }

}