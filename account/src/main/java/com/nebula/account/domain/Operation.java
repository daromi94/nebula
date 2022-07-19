package com.nebula.account.domain;

import com.nebula.shared.domain.commons.value.Id;
import com.nebula.shared.domain.commons.value.Money;

public record Operation(Id accountId, Money money) { }
