package com.diraq.amigoapp.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

public class ApiException {

  private final String message;
  private final HttpStatus httpStatus;
  private final ZonedDateTime zonedDateTime;

  public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
    this.message = message;
    this.httpStatus = httpStatus;
    this.zonedDateTime = zonedDateTime;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public ZonedDateTime getZonedDateTime() {
    return zonedDateTime;
  }
}
