package com.nebula.fraud.application.port.out;

import com.nebula.fraud.domain.Fraudster;
import java.util.List;

public interface FraudsterRepository {

  List<Fraudster> search();
}