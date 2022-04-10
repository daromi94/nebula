package com.nebula.shared.adapter.amqp.customer;

import com.nebula.shared.adapter.amqp.CommonQueueConfiguration;
import com.nebula.shared.domain.customer.CustomerCreated;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CustomerCreatedQueueConfiguration extends CommonQueueConfiguration {

    @Value("${amqp.queues.customer-created}")
    private String customerCreatedQueue;

    @Bean
    @Qualifier("${amqp.queues.customer-created}")
    public Queue customerCreatedQueue() {
        return new Queue(customerCreatedQueue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.customer-created}")
    public Binding internalToCustomerCreatedBinding() {
        return BindingBuilder.bind(customerCreatedQueue())
                .to(internalTopicExchange())
                .with(CustomerCreated.class.getName());
    }

}