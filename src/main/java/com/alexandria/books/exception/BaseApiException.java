package com.alexandria.books.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
  value = {
    "cause",
    "stackTrace",
    "suppressed",
    "localizedMessage",
    "responseHeaders",
    "mostSpecificCause",
    "rootCause",
    "rawStatusCode",
  }
)
public class BaseApiException extends RuntimeException {

  public BaseApiException(String message) {
    super(message);
  }
}
