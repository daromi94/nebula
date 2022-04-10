package com.nebula.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.nebula.fraud", "com.nebula.shared"})
@EnableEurekaClient
class FraudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudApplication.class, args);
    }

}