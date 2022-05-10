package com.nebula.account.adapter.out.persistence.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
class JpaAccount {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "customer_id", nullable = false)
  private String customerId;

  @Column(name = "balance")
  private double balance;
}