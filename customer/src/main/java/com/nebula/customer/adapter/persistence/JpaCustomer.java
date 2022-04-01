package com.nebula.customer.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
class JpaCustomer {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

}