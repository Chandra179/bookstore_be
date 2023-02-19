package com.alexandria.books.mock;

import com.alexandria.books.book.author.Author;
import com.alexandria.books.book.author.AuthorRepository;
import com.alexandria.books.book.Book;
import com.alexandria.books.book.BookRepository;
import com.alexandria.books.exception.InternalServerErrorException;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.book.genre.Genre;
import com.alexandria.books.book.genre.GenreRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Tag(name = "Mock")
@RequestMapping("/v1/mock")
@RestController
public class MockController {

  @Autowired
  BookRepository bookRepository;
  @Autowired
  AuthorRepository authorRepository;
  @Autowired
  GenreRepository genreRepository;

  @Operation(description = "Create book mock data")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success get genre")})
  @GetMapping(value = "")
  @Transactional(rollbackOn = Exception.class)
  public Book createBookMockData() {
    try {
      var book = Book.builder().title("Advanture of kora")
        .authors(new HashSet<>()).genres(new HashSet<>()).build();
      var author1 = Author.builder().firstName("stanley").lastName("hudson").build();
      var author2 = Author.builder().firstName("dwight").lastName("schrute").build();
      var author3 = Author.builder().firstName("michael").lastName("scott").build();
      var author4 = Author.builder().firstName("jim").lastName("halpert").build();
      var author5 = Author.builder().firstName("pam").lastName("halpert").build();
      var authorList = Set.of(author1, author2, author3, author4, author5);

      Set<Author> newAuthors = new HashSet<>();
      authorList.stream().forEach(
        x -> authorRepository
          .findByFirstNameAndLastName(x.getFirstName().toLowerCase(), x.getLastName().toLowerCase())
          .ifPresentOrElse(s -> book.getAuthors().add(s), () -> newAuthors.add(x))
      );
      authorRepository.saveAll(newAuthors);
      book.getAuthors().addAll(newAuthors);

      var genres = new HashSet<>(genreRepository
        .findByNameIn(Set.of(Genre.GENRE.BIOGRAPHY, Genre.GENRE.FICTION))
        .orElseThrow(() -> new NotFoundException("Genre not found")));
      book.setGenres(genres);

      return bookRepository.save(book);
    } catch (Exception e) {
      throw new InternalServerErrorException();
    }
  }

}
