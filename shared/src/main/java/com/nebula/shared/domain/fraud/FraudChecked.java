package com.nebula.shared.domain.fraud;

import com.nebula.shared.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FraudChecked extends DomainEvent {

    private String email;

    public FraudChecked(String aggregateId, String email) {
        super(aggregateId);

        this.email = email;
    }

}