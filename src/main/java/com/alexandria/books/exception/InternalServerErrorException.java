package com.alexandria.books.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerErrorException extends BaseApiException {

  private final HttpStatus errorCode;

  public InternalServerErrorException() {
    super("Internal server error");
    this.errorCode = HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
