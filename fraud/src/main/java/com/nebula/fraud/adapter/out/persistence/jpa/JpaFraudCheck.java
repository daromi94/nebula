package com.nebula.fraud.adapter.out.persistence.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_checks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaFraudCheck {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private boolean isFraudster;

    private LocalDateTime createdAt;

}