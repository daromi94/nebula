package com.nebula.account.consumer;

import com.nebula.account.service.AccountCreator;
import com.nebula.shared.account.domain.AccountBalance;
import com.nebula.shared.account.domain.AccountCreateMessage;
import com.nebula.shared.account.domain.AccountId;
import com.nebula.shared.customer.domain.CustomerId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountCreateConsumer {

    private final AccountCreator creator;

    public AccountCreateConsumer(AccountCreator creator) {
        this.creator = creator;
    }

    @RabbitListener(queues = "${rabbitmq.queue.account}")
    public void consume(AccountCreateMessage message) {
        log.info("new account create message for {}", message);
        AccountId id = new AccountId(message.id());
        CustomerId customerId = new CustomerId(message.customerId());
        AccountBalance balance = new AccountBalance(message.balance());
        creator.create(id, customerId, balance);
    }

}