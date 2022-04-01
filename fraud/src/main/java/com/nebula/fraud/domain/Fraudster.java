package com.nebula.fraud.domain;

import com.nebula.shared.domain.AggregateRoot;
import com.nebula.shared.domain.Email;
import com.nebula.shared.domain.fraud.FraudsterId;

public final class Fraudster extends AggregateRoot {

    private final FraudsterId id;

    private final Email email;

    public Fraudster(FraudsterId id, Email email) {
        this.id    = id;
        this.email = email;
    }

    public FraudsterId id() {
        return id;
    }

    public Email email() {
        return email;
    }

}