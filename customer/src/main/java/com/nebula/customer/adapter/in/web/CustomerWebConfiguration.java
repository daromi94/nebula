package com.nebula.customer.adapter.in.web;

import com.nebula.shared.adapter.web.HttpCustomErrorMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CustomerWebConfiguration {

    @Bean
    public HttpCustomErrorMapper httpCustomErrorMapper() {
        return new HttpCustomErrorMapper();
    }

}