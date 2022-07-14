package com.ridango.payment.db.services;

import com.ridango.payment.db.entity.Account;
import com.ridango.payment.db.entity.Payment;

public interface PaymentService {

  Payment create(Payment payment);

  Payment find(Long paymentId);

}
