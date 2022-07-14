package com.ridango.payment.domain.request;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreatePaymentRequest {

  private Long senderAccountId;

  private Long receiverAccountId;

  private BigDecimal amount;

}
