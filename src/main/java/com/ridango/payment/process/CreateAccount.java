package com.ridango.payment.process;

import static com.ridango.payment.exception.RequestException.isNotNull;
import static com.ridango.payment.exception.RequestException.isTrue;
import static com.ridango.payment.util.BalanceUtil.checkAmountValidity;
import static com.ridango.payment.util.builders.AccountBuilders.buildAccount;
import static com.ridango.payment.util.builders.AccountBuilders.buildAccountResponse;
import com.ridango.payment.db.services.AccountService;
import com.ridango.payment.domain.response.AccountResponse;
import com.ridango.payment.domain.request.CreateAccountRequest;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateAccount {
  private final AccountService accountService;

  public AccountResponse create(CreateAccountRequest accountRequest) {
    validate(accountRequest);
    isTrue(!accountService.existsAccountByName(accountRequest.getName()),
        "Account already exists with given name");
    return buildAccountResponse(accountService.create(buildAccount(accountRequest)));
  }

  private void validate(CreateAccountRequest accountRequest) {
    isNotNull(accountRequest.getName(), "Name cannot be empty");
    isNotNull(accountRequest.getBalance(), "Balance cannot be empty");
    isTrue(checkAmountValidity(accountRequest.getBalance()), "Invalid amount");
  }

}
