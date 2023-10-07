package com.alexandria.books.presentation.api.controller;

import com.alexandria.books.dto.BaseApiResponse;
import com.alexandria.books.domain.service.impl.BookServiceImpl;
import com.alexandria.books.dto.BookApiRequest;
import com.alexandria.books.dto.BookApiResponse;
import com.alexandria.books.domain.model.entity.Genre;
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
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseApiResponse<List<BookApiResponse>> getBooks(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
  ) {
    return BaseApiResponse.build(bookService.findBooks(page, size));
  }

  @Operation(description = "Get books by query param")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseApiResponse<List<BookApiResponse>> getBooks(
    @RequestParam(value = "title", defaultValue = "") String title,
    @RequestParam(value = "genre", defaultValue = "") Genre.GENRE genre
  ) {
    return BaseApiResponse.build(bookService.findBooks(title, genre));
  }

  @Operation(description = "Create book")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  @Transactional(rollbackOn = Exception.class)
  public void createBook(@RequestBody BookApiRequest request) {
    bookService.createBook(request);
  }

}
