package com.nebula.fraud.application.port.out;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.EmailAddress;

import java.util.List;

public interface FraudsterRepository {

    List<Fraudster> searchByEmail(EmailAddress email);

}