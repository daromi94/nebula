package com.nebula.fraud.repository;

import com.nebula.fraud.domain.Fraudster;
import com.nebula.shared.fraud.domain.DubiousEmail;
import com.nebula.shared.fraud.domain.FraudsterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudsterRepository extends JpaRepository<Fraudster, FraudsterId> {

    List<Fraudster> findByEmail(DubiousEmail email);

}