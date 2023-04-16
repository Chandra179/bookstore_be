package com.alexandria.books.service;

import com.alexandria.books.dto.CreateBookRequest;
import com.alexandria.books.dto.CustomAuthorResponse;
import com.alexandria.books.dto.CustomBookResponse;
import com.alexandria.books.entity.Author;
import com.alexandria.books.entity.Book;
import com.alexandria.books.entity.Genre;
import com.alexandria.books.entity.Inventory;
import com.alexandria.books.entity.Pricing;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.repository.AuthorRepository;
import com.alexandria.books.repository.BookRepository;
import com.alexandria.books.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final GenreRepository genreRepository;
  private final AuthorRepository authorRepository;

  @Autowired
  public BookServiceImpl(
    BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository
  ) {
    this.bookRepository = bookRepository;
    this.genreRepository = genreRepository;
    this.authorRepository = authorRepository;
  }

  @Override
  public List<CustomBookResponse> findBooksByPage(int page, int size) {
    var booksPage = bookRepository.findAll(PageRequest.of(page, size));
    var books = booksPage.getContent();
    if (books.isEmpty()) throw new NotFoundException("No books found");
    return books.stream().map(book -> {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(author -> {
        response.getAuthors().add(new CustomAuthorResponse(author.getFirstName(), author.getLastName()));
      });
      return response;
    }).collect(Collectors.toList());
  }

  @Override
  public List<CustomBookResponse> findBooksByRequestParam(String title, Genre.GENRE genre) {
    List<Book> books;
    if (!title.isEmpty() && genre != null) {
      books = bookRepository.findByGenreNameAndTitleContaining(genre, title);
    } else if (genre != null) {
      books = bookRepository.findByGenreName(genre);
    } else if (!title.isEmpty()) {
      books = bookRepository.findByTitleContaining(title);
    } else {
      throw new NotFoundException("Book not found");
    }
    return books.stream().map(book -> {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(
        author -> response.getAuthors().add(new CustomAuthorResponse(author.getFirstName(), author.getLastName()))
      );
      return response;
    }).collect(Collectors.toList());
  }

  @Override
  public void createBook(CreateBookRequest request) {
    var book = Book.builder()
      .title(request.getTitle())
      .authors(new HashSet<>())
      .genres(new HashSet<>()).build();
    Set<Author> authors = request.getAuthors().stream()
      .map(author -> {
        String firstName = author.getFirstName().toLowerCase();
        String lastName = author.getLastName().toLowerCase();
        Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        return existingAuthor.orElse(Author.builder().firstName(firstName).lastName(lastName).build());
      })
      .collect(Collectors.toSet());

    authorRepository.saveAll(authors);
    var genres = new HashSet<>(genreRepository.findByNameIn(request.getGenre())
      .orElseThrow(() -> new NotFoundException("Genre not found")));
    book.setGenres(genres);
    book.getAuthors().addAll(authors);
    book.setInventory(Inventory.builder().book(book).qty(request.getQty()).build());
    book.setPricing(Pricing.builder().book(book).price(request.getPrice()).build());
    bookRepository.save(book);
  }

}
