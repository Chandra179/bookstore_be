package com.alexandria.books.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class ApiErrorResponse {

  private HttpStatusCode code;

  private String message;

  public ApiErrorResponse(HttpStatusCode code, String message) {
    this.code = code;
    this.message = message;
  }

}
