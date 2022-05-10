package com.nebula.shared.util;

public class CustomRuntimeException extends RuntimeException {

  private final CustomError error;

  public CustomRuntimeException(String code, String message) {
    super(message);

    this.error = new CustomError(code, message);
  }

  public CustomRuntimeException(String code, String message, Throwable cause) {
    super(message, cause);

    this.error = new CustomError(code, message, cause.getMessage());
  }

  public CustomError error() {
    return error;
  }
}