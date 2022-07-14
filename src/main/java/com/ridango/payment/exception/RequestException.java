package com.ridango.payment.exception;

public class RequestException extends RuntimeException {
  public RequestException(String error) {
    super(error);
  }

  public static void isNotNull(Object data, String message) throws RequestException {
    if (data == null) {
      throw new RequestException(message);
    }
  }

  public static void isTrue(boolean data, String message) throws RequestException {
    if (!data) {
      throw new RequestException(message);
    }
  }
}
