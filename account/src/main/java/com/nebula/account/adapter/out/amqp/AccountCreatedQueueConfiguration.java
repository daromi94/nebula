package com.nebula.account.adapter.out.amqp;

import com.nebula.shared.adapter.amqp.ExchangeConfiguration;
import com.nebula.shared.domain.account.AccountCreatedEvent;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AccountCreatedQueueConfiguration {

    private final ExchangeConfiguration exchangeConfiguration;

    @Value("${amqp.queues.account-created}")
    private String queue;

    public AccountCreatedQueueConfiguration(ExchangeConfiguration exchangeConfiguration) {
        this.exchangeConfiguration = exchangeConfiguration;
    }

    @Bean
    @Qualifier("${amqp.queues.account-created}")
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.account-created}")
    public Binding internalToAccountCreatedBinding() {
        return BindingBuilder.bind(queue())
                .to(exchangeConfiguration.internalTopicExchange())
                .with(AccountCreatedEvent.class.getName());
    }

}