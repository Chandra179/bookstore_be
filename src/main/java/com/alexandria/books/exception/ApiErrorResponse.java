package com.alexandria.books.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiErrorResponse {

  private HttpStatus code;

  private String message;

  public ApiErrorResponse(HttpStatus code, String message) {
    this.code = code;
    this.message = message;
  }

}
