package com.nebula.shared.adapter.amqp.fraud;

import com.nebula.shared.adapter.amqp.ExchangeConfiguration;
import com.nebula.shared.domain.fraud.FraudChecked;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FraudCheckedQueueConfiguration {

    private final ExchangeConfiguration exchangeConfiguration;

    @Value("${amqp.queues.fraud-checked}")
    private String fraudCheckedQueue;

    private FraudCheckedQueueConfiguration(ExchangeConfiguration exchangeConfiguration) {
        this.exchangeConfiguration = exchangeConfiguration;
    }

    @Bean
    @Qualifier("${amqp.queues.fraud-checked}")
    public Queue fraudCheckedQueue() {
        return new Queue(fraudCheckedQueue);
    }

    @Bean
    @Qualifier("${amqp.exchanges.internal}-${amqp.queues.fraud-checked}")
    public Binding internalToFraudCheckedBinding() {
        return BindingBuilder.bind(fraudCheckedQueue())
                .to(exchangeConfiguration.internalTopicExchange())
                .with(FraudChecked.class.getName());
    }

}