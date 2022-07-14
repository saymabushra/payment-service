package com.ridango.payment.util.builders;

import com.ridango.payment.db.entity.Account;
import com.ridango.payment.domain.response.AccountResponse;
import com.ridango.payment.domain.request.CreateAccountRequest;

public class AccountBuilders {

  private AccountBuilders() {

  }

  public static AccountResponse buildAccountResponse(Account account) {
    return AccountResponse.builder()
        .accountId(account.getId())
        .name(account.getName())
        .balance(account.getBalance())
        .build();
  }

  public static Account buildAccount(CreateAccountRequest accountRequest) {
    return Account.builder()
        .name(accountRequest.getName())
        .balance(accountRequest.getBalance())
        .build();
  }

}
