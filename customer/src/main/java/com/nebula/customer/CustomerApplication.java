package com.nebula.customer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"com.nebula.customer", "com.nebula.shared"})
class CustomerApplication {

  public static void main(String[] args) {
    run(CustomerApplication.class, args);
  }
}
