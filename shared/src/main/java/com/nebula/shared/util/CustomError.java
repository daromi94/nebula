package com.nebula.shared.util;

public class CustomError {

  private final String code;

  private final String message;

  private String cause;

  public CustomError(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public CustomError(String code, String message, String cause) {
    this.code = code;
    this.message = message;
    this.cause = cause;
  }

  public String code() {
    return code;
  }

  public String message() {
    return message;
  }

  public String cause() {
    return cause;
  }
}
