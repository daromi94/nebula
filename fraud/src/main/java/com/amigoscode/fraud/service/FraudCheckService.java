package com.amigoscode.fraud.service;

import com.amigoscode.clients.fraud.dto.FraudCheckResponse;
import com.amigoscode.fraud.domain.FraudCheckHistory;
import com.amigoscode.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public FraudCheckResponse isFraudulentCustomer(String email) {
        boolean isFraudster = false;
        FraudCheckHistory fraudCheckHistory = FraudCheckHistory.builder()
                .email(email)
                .isFraudster(isFraudster)
                .createdAt(LocalDateTime.now())
                .build();
        fraudCheckHistoryRepository.save(fraudCheckHistory);
        return new FraudCheckResponse(isFraudster);
    }

}