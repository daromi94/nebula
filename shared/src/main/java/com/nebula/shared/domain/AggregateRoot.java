package com.nebula.shared.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private List<DomainEvent> events;

    public AggregateRoot() {
        this.events = new ArrayList<>();
    }

    public final List<DomainEvent> pull() {
        List<DomainEvent> domainEvents = this.events;
        this.events = Collections.emptyList();
        return domainEvents;
    }

    protected final void record(DomainEvent event) {
        events.add(event);
    }

}