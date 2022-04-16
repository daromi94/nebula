package com.nebula.customer.adapter.out.amqp;

import com.nebula.shared.adapter.amqp.ExchangeConfiguration;
import com.nebula.shared.domain.customer.CustomerCreatedEvent;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CustomerCreatedQueueConfiguration {

    private final ExchangeConfiguration exchangeConfiguration;

    @Value("${amqp.queues.customer-created}")
    private String queue;

    public CustomerCreatedQueueConfiguration(ExchangeConfiguration exchangeConfiguration) {
        this.exchangeConfiguration = exchangeConfiguration;
    }

    @Bean
    @Qualifier("${amqp.queues.customer-created}")
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.customer-created}")
    public Binding internalToCustomerCreatedBinding() {
        return BindingBuilder.bind(queue())
                .to(exchangeConfiguration.internalTopicExchange())
                .with(CustomerCreatedEvent.class.getName());
    }

}