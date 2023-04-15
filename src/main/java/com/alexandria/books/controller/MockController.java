package com.alexandria.books.controller;

import com.alexandria.books.api.BaseApiResponse;
import com.alexandria.books.dto.CompleteBookResponse;
import com.alexandria.books.entity.Author;
import com.alexandria.books.entity.Book;
import com.alexandria.books.entity.Genre;
import com.alexandria.books.entity.Inventory;
import com.alexandria.books.entity.Pricing;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.repository.AuthorRepository;
import com.alexandria.books.repository.BookRepository;
import com.alexandria.books.repository.GenreRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
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
  public BaseApiResponse<CompleteBookResponse> createBookMockData(
    @RequestParam(value = "title", defaultValue = "") String title,
    @RequestParam(value = "authors", defaultValue = "") Genre.GENRE genre
  ) {
    var book = Book.builder().title(title).authors(new HashSet<>()).genres(new HashSet<>()).build();
    var authorList = Set.of(
      Author.builder().firstName("Stanley").lastName("Hudson").build(),
      Author.builder().firstName("Dwight").lastName("Schrute").build(),
      Author.builder().firstName("Michael").lastName("Scott").build()
    );

    Set<Author> existingAuthors = new HashSet<>();
    Set<Author> newAuthors = new HashSet<>();
    for (Author author : authorList) {
      Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(
        author.getFirstName().toLowerCase(),
        author.getLastName().toLowerCase());
      if (existingAuthor.isPresent()) {
        existingAuthors.add(existingAuthor.get());
        book.getAuthors().add(existingAuthor.get());
      } else {
        newAuthors.add(author);
      }
    }
    authorRepository.saveAll(newAuthors);

    var genres = new HashSet<>(genreRepository
      .findByNameIn(Set.of(Genre.GENRE.BIOGRAPHY, Genre.GENRE.FICTION))
      .orElseThrow(() -> new NotFoundException("Genre not found")));
    book.setGenres(genres);

    book.getAuthors().addAll(newAuthors);
    book.setInventory(Inventory.builder().book(book).qty(BigInteger.valueOf(5)).build());
    book.setPricing(Pricing.builder().book(book).price(BigDecimal.valueOf(100000)).build());
    bookRepository.save(book);

    Set<Author> allAuthors = new HashSet<>(existingAuthors);
    allAuthors.addAll(newAuthors);
    return BaseApiResponse.build(
      CompleteBookResponse.builder().title(book.getTitle()).authors(allAuthors).genres(genres)
        .qty(book.getInventory().getQty()).price(book.getPricing().getPrice()).build()
    );
  }

}
