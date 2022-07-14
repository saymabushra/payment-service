package com.ridango.payment.util;

import java.math.BigDecimal;

public class BalanceUtil {

  public static boolean checkAmountValidity(BigDecimal amount) {
    var scale = Math.max(0, amount.stripTrailingZeros().scale());
    return amount.compareTo(BigDecimal.ZERO) > 0 && (scale == 1 || scale == 2 || scale == 0);
  }

}
