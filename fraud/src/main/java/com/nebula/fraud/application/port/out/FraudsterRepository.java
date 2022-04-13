package com.nebula.fraud.application.port.out;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.domain.value.EmailAddress;
import com.nebula.shared.domain.value.FirstName;
import com.nebula.shared.domain.value.LastName;

import java.util.List;

public interface FraudsterRepository {

    List<Fraudster> searchBySimilarity(FirstName firstName, LastName lastName, EmailAddress email, double threshold);

}