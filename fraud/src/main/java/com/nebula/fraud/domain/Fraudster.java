package com.nebula.fraud.domain;

import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.EmailAddress;
import com.nebula.shared.domain.fraud.FraudsterId;

public final class Fraudster extends AggregateRoot {

    private final FraudsterId id;

    private final EmailAddress email;

    public Fraudster(FraudsterId id, EmailAddress email) {
        this.id    = id;
        this.email = email;
    }

    public FraudsterId id() {
        return id;
    }

    public EmailAddress email() {
        return email;
    }

}