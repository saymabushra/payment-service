package com.ridango.payment.process;

import static com.ridango.payment.exception.RequestException.isNotNull;
import static com.ridango.payment.exception.RequestException.isTrue;
import static com.ridango.payment.util.BalanceUtil.checkAmountValidity;
import static com.ridango.payment.util.builders.PaymentBuilders.buildPayment;
import static com.ridango.payment.util.builders.PaymentBuilders.buildPaymentResponse;
import com.ridango.payment.db.services.AccountService;
import com.ridango.payment.db.services.PaymentService;
import com.ridango.payment.domain.request.CreatePaymentRequest;
import com.ridango.payment.domain.response.PaymentResponse;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class CreatePayment {
  private final PaymentService paymentService;
  private final AccountService accountService;

  public PaymentResponse create(CreatePaymentRequest paymentRequest) {
    validate(paymentRequest);
    var senderAccount = accountService.find(paymentRequest.getSenderAccountId());
    isNotNull(senderAccount, "Sender account not found");
    var receiverAccount = accountService.find(paymentRequest.getReceiverAccountId());
    isNotNull(receiverAccount, "Receiver account not found");
    isTrue(senderAccount.getBalance().compareTo(paymentRequest.getAmount()) >= 0,
        "Insufficient sender balance");
    var payment = paymentService.create(buildPayment(paymentRequest));
    senderAccount.setBalance(senderAccount.getBalance().subtract(paymentRequest.getAmount()));
    receiverAccount.setBalance(receiverAccount.getBalance().add(paymentRequest.getAmount()));
    accountService.update(senderAccount);
    accountService.update(receiverAccount);
    return buildPaymentResponse(payment);
  }

  private void validate(CreatePaymentRequest paymentRequest) {
    isNotNull(paymentRequest.getSenderAccountId(), "Sender account id cannot be empty");
    isNotNull(paymentRequest.getReceiverAccountId(), "Sender account id cannot be empty");
    isNotNull(paymentRequest.getAmount(), "Amount cannot be empty");
    isTrue(checkAmountValidity(paymentRequest.getAmount()), "Invalid amount");
  }

}
