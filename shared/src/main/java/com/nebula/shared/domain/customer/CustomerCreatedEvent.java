package com.nebula.shared.domain.customer;

import com.nebula.shared.domain.commons.DomainEvent;
import com.nebula.shared.domain.commons.value.EmailAddress;
import com.nebula.shared.domain.commons.value.FirstName;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.LastName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class CustomerCreatedEvent extends DomainEvent {

  private String firstName;

  private String lastName;

  private String email;

  public CustomerCreatedEvent(
      Id aggregateId, FirstName firstName, LastName lastName, EmailAddress email) {
    super(aggregateId.value());

    this.firstName = firstName.value();
    this.lastName = lastName.value();
    this.email = email.value();
  }
}