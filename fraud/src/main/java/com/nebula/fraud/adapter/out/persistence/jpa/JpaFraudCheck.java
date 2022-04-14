package com.nebula.fraud.adapter.out.persistence.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "is_fraudster")
    private boolean isFraudster;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}