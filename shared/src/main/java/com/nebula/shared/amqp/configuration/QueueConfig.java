package com.nebula.shared.amqp.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class QueueConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.account}")
    private String accountQueue;

    @Value("${rabbitmq.routing-keys.internal-account}")
    private String internalAccountRoutingKey;

    @Bean
    TopicExchange internalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    Queue accountQueue() {
        return new Queue(accountQueue);
    }

    @Bean
    Binding internalToAccountBinding() {
        return BindingBuilder.bind(accountQueue()).to(internalTopicExchange()).with(internalAccountRoutingKey);
    }

}