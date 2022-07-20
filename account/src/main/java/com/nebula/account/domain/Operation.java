package com.nebula.account.domain;

import com.nebula.shared.domain.value.Id;
import com.nebula.shared.domain.value.Money;

public record Operation(Id accountId, Money money) { }
