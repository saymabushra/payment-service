package com.ridango.payment.controller;

import com.ridango.payment.domain.request.CreatePaymentRequest;
import com.ridango.payment.domain.response.PaymentResponse;
import com.ridango.payment.process.CreatePayment;
import com.ridango.payment.process.queries.PaymentQueries;
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
public class PaymentController {
  private final CreatePayment createPayment;
  private final PaymentQueries paymentQueries;

  @PostMapping("/payments")
  @Transactional
  public ResponseEntity<PaymentResponse> createPayment(
      @RequestBody CreatePaymentRequest paymentRequest
  ) {
    return new ResponseEntity<>(createPayment.create(paymentRequest), HttpStatus.OK);
  }

  @GetMapping("/payments/{paymentId}")
  public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long paymentId) {
    return new ResponseEntity<>(paymentQueries.find(paymentId), HttpStatus.OK);
  }
}
