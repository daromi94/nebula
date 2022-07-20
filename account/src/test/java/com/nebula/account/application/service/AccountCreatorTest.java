package com.nebula.account.application.service;

import com.nebula.account.application.port.in.AccountCreateCommand;
import com.nebula.account.application.port.out.SaveAccountPort;
import com.nebula.account.application.port.out.SearchAccountPort;
import com.nebula.account.domain.Account;
import com.nebula.shared.domain.value.Id;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class AccountCreatorTest {

  private static final Id ACCOUNT_ID = new Id("917d15ee");

  private static final Id USER_ID = new Id("825e17ff");

  @InjectMocks AccountCreator underTest;

  @Mock SearchAccountPort searcher;

  @Mock SaveAccountPort saver;

  @Test
  void givenExistingAccountId_thenAccountCreationShouldFail() {
    given(searcher.exists(ACCOUNT_ID)).willReturn(true);

    AccountCreateCommand command = new AccountCreateCommand(ACCOUNT_ID, USER_ID);

    // TODO: throw a dedicated exception
    assertThrows(RuntimeException.class, () -> underTest.create(command));

    then(searcher).should().exists(ACCOUNT_ID);
    then(saver).should(never()).save(any());
  }

  @Test
  void givenNonexistentAccountId_thenAccountCreationShouldSucceed() {
    given(searcher.exists(ACCOUNT_ID)).willReturn(false);

    Optional<Account> optionalAccount =
        underTest.create(new AccountCreateCommand(ACCOUNT_ID, USER_ID));

    assertThat(optionalAccount).isNotEmpty();

    then(searcher).should().exists(ACCOUNT_ID);
    then(saver).should().save(optionalAccount.get());
  }
}
