package com.nebula.account.domain;

import com.nebula.shared.domain.value.Id;

import java.util.List;

public record Bundle(Id id, List<Operation> operations) { }
