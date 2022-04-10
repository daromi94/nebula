package com.nebula.shared.application.service;

import com.nebula.shared.adapter.amqp.ExchangeConfiguration;
import com.nebula.shared.domain.event.DomainEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventPublisher {

    private final AmqpTemplate template;

    private final ExchangeConfiguration exchangeConfiguration;

    public EventPublisher(AmqpTemplate template, ExchangeConfiguration exchangeConfiguration) {
        this.template              = template;
        this.exchangeConfiguration = exchangeConfiguration;
    }

    public void publish(List<DomainEvent> events) {
        String exchange = exchangeConfiguration.internalTopicExchange().getName();

        events.forEach(event -> template.convertAndSend(exchange, event.getClass().getName(), event));
    }

    public void publish(DomainEvent event) {
        publish(List.of(event));
    }

}