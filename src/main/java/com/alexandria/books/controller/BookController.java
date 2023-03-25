package com.alexandria.books.controller;

import com.alexandria.books.dto.CustomBookResponse;
import com.alexandria.books.entity.Genre;
import com.alexandria.books.service.BookServiceImpl;
import com.alexandria.books.validator.ValidUUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Book")
@RequestMapping("/books")
@RestController
public class BookController {

  @Autowired
  BookServiceImpl bookService;

  @Operation(description = "Find all books, zero based index")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success get books"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<CustomBookResponse> findAllBooks(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
  ) {
    return bookService.findAllBooks(page, size);
  }

  @Operation(description = "Find books by query param")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success get book"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @GetMapping(value = "")
  public List<CustomBookResponse> findBooksByQueryParam(
    @ValidUUID @RequestParam(value = "book_id", defaultValue = "") String id,
    @RequestParam(value = "book_title", defaultValue = "") String title,
    @RequestParam(value = "genre", defaultValue = "") Genre.GENRE genre
  ) {
    return bookService.findBooksByQueryParam(id, title, genre);
  }

}
