package com.ridango.payment.process.queries;

import static com.ridango.payment.util.builders.PaymentBuilders.buildPaymentResponse;
import com.ridango.payment.db.services.PaymentService;
import com.ridango.payment.domain.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentQueries {
  private final PaymentService paymentService;

  public PaymentResponse find(Long paymentId) {
    return buildPaymentResponse(paymentService.find(paymentId));
  }
}
