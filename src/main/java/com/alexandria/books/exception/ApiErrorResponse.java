package com.alexandria.books.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiErrorResponse {

  private HttpStatus errorCode;

  private String errorMessage;

  public ApiErrorResponse(HttpStatus errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
