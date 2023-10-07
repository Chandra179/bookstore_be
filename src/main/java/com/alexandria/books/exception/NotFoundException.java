package com.alexandria.books.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends BaseApiException {

  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
