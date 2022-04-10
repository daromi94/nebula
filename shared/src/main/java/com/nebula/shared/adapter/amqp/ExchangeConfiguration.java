package com.nebula.shared.adapter.amqp;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfiguration {

    @Value("${amqp.exchanges.internal}")
    private String internalTopicExchange;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalTopicExchange);
    }

}