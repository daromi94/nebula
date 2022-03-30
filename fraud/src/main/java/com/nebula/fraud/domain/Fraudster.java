package com.nebula.fraud.domain;


import com.nebula.shared.fraud.domain.DubiousEmail;
import com.nebula.shared.fraud.domain.FraudsterId;
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
@Table(name = "fraudsters")
public class Fraudster {

    @Id
    @Embedded
    private FraudsterId id;

    @Embedded
    private DubiousEmail email;

}