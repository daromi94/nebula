package com.nebula.shared.adapter.amqp.account;

import com.nebula.shared.adapter.amqp.ExchangeConfiguration;
import com.nebula.shared.domain.account.AccountCreated;
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
    private String accountCreatedQueue;

    public AccountCreatedQueueConfiguration(ExchangeConfiguration exchangeConfiguration) {
        this.exchangeConfiguration = exchangeConfiguration;
    }

    @Bean
    @Qualifier("${amqp.queues.account-created}")
    public Queue accountCreatedQueue() {
        return new Queue(accountCreatedQueue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.account-created}")
    public Binding internalToAccountCreatedBinding() {
        return BindingBuilder.bind(accountCreatedQueue())
                .to(exchangeConfiguration.internalTopicExchange())
                .with(AccountCreated.class.getName());
    }

}