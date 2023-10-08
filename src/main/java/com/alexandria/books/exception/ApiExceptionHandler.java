package com.alexandria.books.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex) {
    ApiErrorResponse error = new ApiErrorResponse(ex.getStatusCode(), ex.getMessage());
    return new ResponseEntity<>(error, ex.getStatusCode());
  }

  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<ApiErrorResponse> handleInvalidFormatException(InvalidFormatException  ex) {
    ApiErrorResponse error = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Invalid format");
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintDefinitionException.class)
  public ResponseEntity<ApiErrorResponse> handleConstraintException(ConstraintDefinitionException  ex) {
    ApiErrorResponse error = new ApiErrorResponse(
      HttpStatus.BAD_REQUEST, "The input does not meet the required format constraints"
    );
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
