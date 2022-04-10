package com.nebula.shared.domain.event;

import java.util.UUID;

public abstract class DomainEvent {

    private static final String NO_AGGREGATE = "";

    private final String id;

    private final String aggregateId;

    public DomainEvent() {
        this.id          = UUID.randomUUID().toString();
        this.aggregateId = NO_AGGREGATE;
    }

    public DomainEvent(String aggregateId) {
        this.id          = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
    }
    
    public String getId() {
        return id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

}