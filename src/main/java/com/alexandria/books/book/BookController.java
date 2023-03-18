package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Book")
@RequestMapping("/books")
@RestController
public class BookController {

  @Autowired
  BookServiceImpl bookService;

  @Operation(description = "Get all books, zero based index")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success get books"),
      @ApiResponse(responseCode = "404", description = "Book not found")
  })
  @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<BookPaginationResponse> getAllBooks(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size
  ) throws ResponseStatusException {
    return bookService.findAllBooks(page, size);
  }

  @Operation(description = "Find book by title")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success get book"),
      @ApiResponse(responseCode = "404", description = "Book not found",
        content = @Content(schema = @Schema(implementation = ResponseStatusException.class)))
  })
  @GetMapping(value = "")
  public List<Book> findBooksByQueryParam(
    @RequestParam(value = "id", defaultValue = "") String id,
    @RequestParam(value = "book_title", defaultValue = "") String title,
    @RequestParam(value = "genre", defaultValue = "") Genre.GENRE genre
  ) {
    return bookService.findBooksByQueryParam(id, title, genre);
  }

}
