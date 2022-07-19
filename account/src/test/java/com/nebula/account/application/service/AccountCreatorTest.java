package com.nebula.account.application.service;

import com.nebula.account.application.port.in.AccountCreateCommand;
import com.nebula.account.application.port.out.SaveAccountPort;
import com.nebula.account.application.port.out.SearchAccountPort;
import com.nebula.account.domain.Account;
import com.nebula.shared.domain.commons.value.Id;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class AccountCreatorTest {

  @InjectMocks AccountCreator underTest;

  @Mock SearchAccountPort searcher;

  @Mock SaveAccountPort saver;

  @Test
  void givenExistingAccountId_thenAccountCreationShouldFail() {
    Id id = new Id("917d15ee");

    given(searcher.exists(id)).willReturn(true);

    Optional<Account> optionalAccount =
        underTest.create(new AccountCreateCommand(id, new Id("825e17ff")));

    assertThat(optionalAccount).isEmpty();

    then(searcher).should().exists(id);
    then(saver).should(never()).save(any());
  }

  @Test
  void givenNonexistentAccountId_thenAccountCreationShouldSucceed() {
    Id id = new Id("917d15ee");

    given(searcher.exists(id)).willReturn(false);

    Optional<Account> optionalAccount =
        underTest.create(new AccountCreateCommand(id, new Id("825e17ff")));

    assertThat(optionalAccount).isNotEmpty();

    then(searcher).should().exists(id);
    then(saver).should().save(optionalAccount.get());
  }
}
