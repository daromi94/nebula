package com.nebula.shared.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent {

    private final String id;

    private final String aggregateId;

    private final LocalDateTime occurredOn;

    public DomainEvent(String aggregateId) {
        this.id          = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.occurredOn  = LocalDateTime.now();
    }

    public abstract String name();

    public String id() {
        return id;
    }

    public String aggregateId() {
        return aggregateId;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }

}