package com.nebula.account.domain;

import com.nebula.shared.account.domain.AccountBalance;
import com.nebula.shared.account.domain.AccountId;
import com.nebula.shared.customer.domain.CustomerId;
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
@Table(name = "accounts")
public class Account {

    @Id
    @Embedded
    private AccountId id;

    @Embedded
    private CustomerId customerId;

    @Embedded
    private AccountBalance balance;

}