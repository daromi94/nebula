package com.nebula.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"com.nebula.account", "com.nebula.shared"})
class AccountApplication {

  public static void main(String[] args) {
    run(AccountApplication.class, args);
  }
}
