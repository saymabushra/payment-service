package com.ridango.payment.domain.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaymentResponse {

  private Long paymentId;

  private Long senderAccountId;

  private Long receiverAccountId;

  private BigDecimal amount;

}
