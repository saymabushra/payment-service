package com.ridango.payment.db.services;

import com.ridango.payment.db.entity.Account;

public interface AccountService {

  Account create(Account account);

  Account update(Account account);

  Account find(Long accountId);

  boolean existsAccountByName(String name);

}
