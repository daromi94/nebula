package com.nebula.fraud.adapter.out.amqp;

import com.nebula.shared.adapter.amqp.ExchangeConfiguration;
import com.nebula.shared.domain.fraud.FraudCheckCreatedEvent;
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

  @Value("${amqp.queues.fraud-check-created}")
  private String queue;

  public FraudCheckedQueueConfiguration(ExchangeConfiguration exchangeConfiguration) {
    this.exchangeConfiguration = exchangeConfiguration;
  }

  @Bean
  @Qualifier("${amqp.queues.fraud-check-created}")
  public Queue queue() {
    return new Queue(queue);
  }

  @Bean
  @Qualifier("${amqp.exchanges.internal}-${amqp.queues.fraud-check-created}")
  public Binding internalToFraudCheckedBinding() {
    return BindingBuilder.bind(queue())
        .to(exchangeConfiguration.internalTopicExchange())
        .with(FraudCheckCreatedEvent.class.getName());
  }
}