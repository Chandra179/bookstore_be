package com.alexandria.books.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
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

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundException(InvalidFormatException  ex) {
    ApiErrorResponse error = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Invalid format");
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
