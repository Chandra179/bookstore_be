package com.alexandria.books.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerErrorException extends BaseApiException {

  public InternalServerErrorException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
