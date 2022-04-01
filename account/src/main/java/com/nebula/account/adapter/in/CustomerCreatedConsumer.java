package com.nebula.account.adapter.in;

import com.nebula.account.application.service.AccountCreator;
import com.nebula.shared.domain.account.AccountId;
import com.nebula.shared.domain.customer.CustomerCreated;
import com.nebula.shared.domain.customer.CustomerId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
final class CustomerCreatedConsumer {

    private final AccountCreator creator;

    public CustomerCreatedConsumer(AccountCreator creator) {
        this.creator = creator;
    }

    @RabbitListener(queues = "${rabbitmq.queue.account}")
    public void consume(CustomerCreated event) {
        log.info("new account created event for {}", event);

        AccountId  id         = new AccountId(event.id());
        CustomerId customerId = new CustomerId(event.aggregateId());

        creator.create(id, customerId);
    }

}