package com.alexandria.books.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

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
public class BaseApiException extends ResponseStatusException {

  public BaseApiException(HttpStatusCode status, String reason) {
    super(status, reason);
  }
  public BaseApiException(HttpStatusCode status) {
    super(status);
  }
}
