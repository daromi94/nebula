package com.nebula.fraud;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nebula.fraud", "com.nebula.shared"})
class FraudApplication {

  public static void main(String[] args) {
    run(FraudApplication.class, args);
  }
}
