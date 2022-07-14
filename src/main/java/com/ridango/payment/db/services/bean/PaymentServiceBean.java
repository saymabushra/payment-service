package com.ridango.payment.db.services.bean;

import com.ridango.payment.db.entity.Account;
import com.ridango.payment.db.entity.Payment;
import com.ridango.payment.db.repository.AccountRepository;
import com.ridango.payment.db.repository.PaymentRepository;
import com.ridango.payment.db.services.AccountService;
import com.ridango.payment.db.services.PaymentService;
import javax.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceBean implements PaymentService {

  private final PaymentRepository paymentRepository;

  @Override
  public Payment create(Payment payment) {
    return paymentRepository.save(payment);
  }

  @Override
  public Payment find(Long paymentId) {
    return paymentRepository.findById(paymentId).orElseThrow(
        () -> new PersistenceException("Payment not found with given id")
    );
  }

}
