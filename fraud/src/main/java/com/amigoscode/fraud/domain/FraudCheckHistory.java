package com.amigoscode.fraud.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "fraud_check_history")
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(name = "fraud_id_sequence", sequenceName = "fraud_id_sequence")
    @GeneratedValue(generator = "fraud_id_sequence", strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String customerNumber;

    private Boolean isFraudster;

    private LocalDateTime createdAt;

}