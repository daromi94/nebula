package com.nebula.customer;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nebula.customer", "com.nebula.shared"})
class CustomerApplication {

  public static void main(String[] args) {
    run(CustomerApplication.class, args);
  }
}
