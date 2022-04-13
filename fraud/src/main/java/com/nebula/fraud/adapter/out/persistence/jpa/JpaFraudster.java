package com.nebula.fraud.adapter.out.persistence.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fraudsters")
@Data
@AllArgsConstructor
@NoArgsConstructor
class JpaFraudster {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

}