package com.alexandria.books.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex) {
    ApiErrorResponse error = new ApiErrorResponse(ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(error, ex.getErrorCode());
  }
}
