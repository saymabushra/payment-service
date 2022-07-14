package com.ridango.payment.process.queries;

import static com.ridango.payment.util.builders.AccountBuilders.buildAccountResponse;
import com.ridango.payment.db.services.AccountService;
import com.ridango.payment.domain.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountQueries {
  private final AccountService accountService;

  public AccountResponse find(Long accountId) {
    return buildAccountResponse(accountService.find(accountId));
  }
}
