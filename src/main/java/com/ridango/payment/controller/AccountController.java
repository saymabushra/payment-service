package com.ridango.payment.controller;

import com.ridango.payment.domain.response.AccountResponse;
import com.ridango.payment.domain.request.CreateAccountRequest;
import com.ridango.payment.process.CreateAccount;
import com.ridango.payment.process.queries.AccountQueries;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController {
  private final CreateAccount createAccount;
  private final AccountQueries accountQueries;

  @PostMapping("/accounts")
  @Transactional
  public ResponseEntity<AccountResponse> createAccount(
      @RequestBody CreateAccountRequest accountRequest
  ) {
    return new ResponseEntity<>(createAccount.create(accountRequest), HttpStatus.OK);
  }

  @GetMapping("/accounts/{accountId}")
  public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
    accountQueries.find(accountId);
    return new ResponseEntity<>(accountQueries.find(accountId), HttpStatus.OK);
  }
}
