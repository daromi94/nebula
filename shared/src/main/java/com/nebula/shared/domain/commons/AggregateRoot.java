package com.nebula.shared.domain.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

  private List<DomainEvent> events;

  protected AggregateRoot() {
    this.events = new ArrayList<>();
  }

  public final List<DomainEvent> pull() {
    var domainEvents = this.events;
    this.events = Collections.emptyList();
    return domainEvents;
  }

  protected final void register(DomainEvent event) {
    events.add(event);
  }
}
