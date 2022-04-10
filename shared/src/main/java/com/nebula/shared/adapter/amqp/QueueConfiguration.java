package com.nebula.shared.adapter.amqp;

import com.nebula.shared.domain.account.AccountCreated;
import com.nebula.shared.domain.customer.CustomerCreated;
import com.nebula.shared.domain.fraud.FraudChecked;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class QueueConfiguration {

    @Value("${amqp.exchanges.internal}")
    private String internalTopicExchange;

    @Value("${amqp.queues.account-created}")
    private String accountCreatedQueue;

    @Value("${amqp.queues.customer-created}")
    private String customerCreatedQueue;

    @Value("${amqp.queues.fraud-checked}")
    private String fraudCheckedQueue;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalTopicExchange);
    }

    @Bean
    @Qualifier("${amqp.queues.account-created}")
    public Queue accountCreatedQueue() {
        return new Queue(accountCreatedQueue);
    }

    @Bean
    @Qualifier("${amqp.queues.customer-created}")
    public Queue customerCreatedQueue() {
        return new Queue(customerCreatedQueue);
    }

    @Bean
    @Qualifier("${amqp.queues.fraud-checked}")
    public Queue fraudCheckedQueue() {
        return new Queue(fraudCheckedQueue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.account-created}")
    public Binding internalToAccountCreatedBinding() {
        return BindingBuilder.bind(accountCreatedQueue())
                .to(internalTopicExchange())
                .with(AccountCreated.class.getName());
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.customer-created}")
    public Binding internalToCustomerCreatedBinding() {
        return BindingBuilder.bind(customerCreatedQueue())
                .to(internalTopicExchange())
                .with(CustomerCreated.class.getName());
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.fraud-checked}")
    public Binding internalToFraudCheckedBinding() {
        return BindingBuilder.bind(fraudCheckedQueue()).to(internalTopicExchange()).with(FraudChecked.class.getName());
    }

}