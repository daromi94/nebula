package com.nebula.account;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nebula.account", "com.nebula.shared"})
class AccountApplication {

  public static void main(String[] args) {
    run(AccountApplication.class, args);
  }
}