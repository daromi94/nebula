package com.nebula.customer.domain;

import com.nebula.shared.customer.domain.CustomerEmail;
import com.nebula.shared.customer.domain.CustomerFirstName;
import com.nebula.shared.customer.domain.CustomerId;
import com.nebula.shared.customer.domain.CustomerLastName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @Embedded
    private CustomerId id;

    @Embedded
    private CustomerFirstName firstName;

    @Embedded
    private CustomerLastName lastName;

    @Embedded
    private CustomerEmail email;

}