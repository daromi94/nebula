package com.nebula.shared.adapter.amqp.account;

import com.nebula.shared.adapter.amqp.CommonQueueConfiguration;
import com.nebula.shared.domain.account.AccountCreated;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AccountCreatedQueueConfiguration extends CommonQueueConfiguration {

    @Value("${amqp.queues.account-created}")
    private String accountCreatedQueue;

    @Bean
    @Qualifier("${amqp.queues.account-created}")
    public Queue accountCreatedQueue() {
        return new Queue(accountCreatedQueue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.account-created}")
    public Binding internalToAccountCreatedBinding() {
        return BindingBuilder.bind(accountCreatedQueue())
                .to(internalTopicExchange())
                .with(AccountCreated.class.getName());
    }

}