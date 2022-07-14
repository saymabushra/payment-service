package com.ridango.payment.db.repository;

import com.ridango.payment.db.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
  boolean existsAccountByName(String name);
}
