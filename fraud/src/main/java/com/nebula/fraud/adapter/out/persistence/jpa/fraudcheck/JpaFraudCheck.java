package com.nebula.fraud.adapter.out.persistence.jpa.fraudcheck;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fraud_checks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpaFraudCheck {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "is_fraudster")
  private boolean isFraudster;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
}