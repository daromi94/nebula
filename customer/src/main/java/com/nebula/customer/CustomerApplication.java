package com.nebula.customer;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.nebula.customer", "com.nebula.shared"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.nebula.shared")
class CustomerApplication {

  public static void main(String[] args) {
    run(CustomerApplication.class, args);
  }
}