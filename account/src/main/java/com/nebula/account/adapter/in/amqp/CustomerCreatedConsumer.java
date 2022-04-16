package com.nebula.account.adapter.in.amqp;

import com.nebula.account.application.service.AccountCreator;
import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.customer.CustomerCreatedEvent;
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

    @RabbitListener(queues = "${amqp.queues.customer-created}")
    public void consume(CustomerCreatedEvent event) {
        log.info("customer created event for {}", event);

        var id         = Id.of(event.getId());
        var customerId = Id.of(event.getAggregateId());

        creator.create(id, customerId);
    }

}