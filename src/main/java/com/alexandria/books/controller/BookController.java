package com.alexandria.books.controller;

import com.alexandria.books.dto.CreateBookRequest;
import com.alexandria.books.dto.CustomBookResponse;
import com.alexandria.books.entity.Genre;
import com.alexandria.books.service.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Operation(description = "Get books per page, zero based index")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<CustomBookResponse> getBooksByPage(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
  ) {
    return bookService.findBooksByPage(page, size);
  }

  @Operation(description = "Get books by query param")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<CustomBookResponse> getBooksByRequestParam(
    @RequestParam(value = "title", defaultValue = "") String title,
    @RequestParam(value = "genre", defaultValue = "") Genre.GENRE genre
  ) {
    return bookService.findBooksByRequestParam(title, genre);
  }

  @Operation(description = "Create book")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
  @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
  @Transactional(rollbackOn = Exception.class)
  public void createBook(@RequestBody CreateBookRequest request) {
    bookService.createBook(request);
  }

}
