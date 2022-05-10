package com.nebula.shared.application.service;

import com.nebula.shared.util.CustomRuntimeException;

public final class EventPublishingErrorException extends CustomRuntimeException {

  public static final String ERROR_CODE = "events-publishing-failed";

  public EventPublishingErrorException(String exchange, Throwable cause) {
    super(ERROR_CODE, String.format("Event publishing failed at exchange <%s>!", exchange), cause);
  }
}
