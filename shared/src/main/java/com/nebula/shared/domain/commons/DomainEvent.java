package com.nebula.shared.domain.commons;

import java.util.UUID;

public abstract class DomainEvent {

  private final String id;

  private final String aggregateId;

  protected DomainEvent() {
    this.id = null;
    this.aggregateId = null;
  }

  protected DomainEvent(String aggregateId) {
    this.id = UUID.randomUUID().toString();
    this.aggregateId = aggregateId;
  }

  public String getId() {
    return id;
  }

  public String getAggregateId() {
    return aggregateId;
  }
}