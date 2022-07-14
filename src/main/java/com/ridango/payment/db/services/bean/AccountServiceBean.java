package com.ridango.payment.db.services.bean;

import com.ridango.payment.db.entity.Account;
import com.ridango.payment.db.repository.AccountRepository;
import com.ridango.payment.db.services.AccountService;
import javax.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceBean implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public Account create(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Account update(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Account find(Long accountId) {
    return accountRepository.findById(accountId).orElseThrow(
        () -> new PersistenceException("Account not found with given id")
    );
  }

  @Override
  public boolean existsAccountByName(String name) {
    return accountRepository.existsAccountByName(name);
  }

}
