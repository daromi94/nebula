package com.nebula.fraud;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.nebula.fraud", "com.nebula.shared"})
@EnableEurekaClient
class FraudApplication {

  public static void main(String[] args) {
    run(FraudApplication.class, args);
  }
}