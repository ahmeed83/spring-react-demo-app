package com.diraq.amigoapp.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

  @ExceptionHandler(value = {ApiRequestException.class})
  ResponseEntity<Object> handelApiRequestException(ApiRequestException e) {
    final var apiException = new ApiException(
        e.getMessage(),
        BAD_REQUEST,
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, BAD_REQUEST);
  }

}
