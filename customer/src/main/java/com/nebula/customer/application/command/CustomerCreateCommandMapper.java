package com.nebula.customer.application.command;

import com.nebula.shared.adapter.web.customer.CustomersPostRequest;
import com.nebula.shared.domain.commons.value.Id;
import org.springframework.stereotype.Component;

@Component
public final class CustomerCreateCommandMapper {

  public CustomerCreateCommand fromRequest(CustomersPostRequest request) {
    var id = Id.of(request.getId());
    var firstName = FirstName.of(request.getFirstName());
    var lastName = LastName.of(request.getLastName());
    var email = EmailAddress.of(request.getEmail());

    return new CustomerCreateCommand(id, firstName, lastName, email);
  }
}
