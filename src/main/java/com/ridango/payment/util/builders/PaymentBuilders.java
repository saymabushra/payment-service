package com.ridango.payment.util.builders;

import com.ridango.payment.db.entity.Payment;
import com.ridango.payment.domain.request.CreatePaymentRequest;
import com.ridango.payment.domain.response.PaymentResponse;

public class PaymentBuilders {

  private PaymentBuilders(){

  }

  public static PaymentResponse buildPaymentResponse(Payment payment) {
    return PaymentResponse.builder()
        .paymentId(payment.getId())
        .senderAccountId(payment.getSenderAccountId())
        .receiverAccountId(payment.getReceiverAccountId())
        .amount(payment.getAmount())
        .build();
  }

  public static Payment buildPayment(CreatePaymentRequest paymentRequest) {
    return Payment.builder()
        .senderAccountId(paymentRequest.getSenderAccountId())
        .receiverAccountId(paymentRequest.getReceiverAccountId())
        .amount(paymentRequest.getAmount().setScale(2))
        .build();
  }

}
