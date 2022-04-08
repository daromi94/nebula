package com.nebula.shared.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventPublisher {

    private final AmqpTemplate template;

    public EventPublisher(AmqpTemplate template) {
        this.template = template;
    }

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("publishing to exchange {} using routing key {} - payload {}", exchange, routingKey, payload);

        template.convertAndSend(exchange, routingKey, payload);

        log.info("published to exchange {} using routing key {} - payload {}", exchange, routingKey, payload);
    }

}