package com.alexandria.books.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends BaseApiException {

  private final HttpStatus errorCode;

  public NotFoundException(String message) {
    super(message);
    this.errorCode = HttpStatus.NOT_FOUND;
  }
}
