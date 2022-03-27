package com.amigoscode.fraud.service;

import com.amigoscode.fraud.domain.FraudCheckHistory;
import com.amigoscode.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public boolean isFraudulentCustomer(String customerNumber) {
        boolean isFraudster = false;
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
                .customerNumber(customerNumber)
                .isFraudster(isFraudster)
                .createdAt(LocalDateTime.now())
                .build();
        fraudCheckHistoryRepository.save(fraudCheckHistory);
        return isFraudster;
    }

}