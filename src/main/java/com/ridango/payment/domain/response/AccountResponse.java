package com.ridango.payment.domain.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccountResponse {

  private Long accountId;

  private String name;

  private BigDecimal balance;

}
