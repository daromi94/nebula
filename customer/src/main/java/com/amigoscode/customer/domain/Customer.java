package com.amigoscode.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
    @GeneratedValue(generator = "customer_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String customerNumber;

    private String firstName;

    private String lastName;

    private String email;

}