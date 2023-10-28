package com.alexandria.books.service.impl;

import com.alexandria.books.factories.BookBuilder;
import com.alexandria.books.service.BookService;
import com.alexandria.books.model.Inventory;
import com.alexandria.books.repository.InventoryRepository;
import com.alexandria.books.dto.BookApiResponse;
import com.alexandria.books.dto.BookApiRequest;
import com.alexandria.books.dto.AuthorDto;
import com.alexandria.books.model.Author;
import com.alexandria.books.model.Book;
import com.alexandria.books.model.Genre;
import com.alexandria.books.valueobjects.Price;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.repository.AuthorRepository;
import com.alexandria.books.repository.BookRepository;
import com.alexandria.books.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final GenreRepository genreRepository;
  private final AuthorRepository authorRepository;
  private final InventoryRepository inventoryRepository;

  @Autowired
  public BookServiceImpl(
    BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository,
    InventoryRepository inventoryRepository
  ) {
    this.bookRepository = bookRepository;
    this.genreRepository = genreRepository;
    this.authorRepository = authorRepository;
    this.inventoryRepository = inventoryRepository;
  }

  @Override
  public List<BookApiResponse> findBooks(int page, int size) {
    List<BookApiResponse> bookResponses = bookRepository.findAll(PageRequest.of(page, size))
      .getContent()
      .stream()
      .map(book -> new BookApiResponse(
        book.getTitle(),
        book.getAuthors().stream()
          .map(author -> new AuthorDto(author.getFirstName(), author.getLastName()))
          .collect(Collectors.toList())))
      .collect(Collectors.toList());
    if (bookResponses.isEmpty()) throw new NotFoundException("Books not found");
    return bookResponses;
  }

  @Override
  public List<BookApiResponse> findBooks(String title, Genre.GENRE genre) {
    List<Book> books;
    // TODO: use strategy pattern if the conditional checking become more complex
    if (!title.isEmpty() && genre != null) {
      books = bookRepository.findByGenreNameAndTitleContaining(genre, title);
    } else if (genre != null) {
      books = bookRepository.findByGenreName(genre);
    } else if (!title.isEmpty()) {
      books = bookRepository.findByTitleContaining(title);
    } else {
      throw new UnsupportedOperationException();
    }
    return books.stream()
      .map(book -> new BookApiResponse(
        book.getTitle(),
        book.getAuthors().stream()
          .map(author -> new AuthorDto(author.getFirstName(), author.getLastName()))
          .collect(Collectors.toList())))
      .collect(Collectors.toList());
  }

  @Override
  public void createBook(BookApiRequest request) {
    Set<Author> newAuthors = new HashSet<>();
    Set<Author> authors = request.getAuthors().stream()
      .map(author -> {
        String firstName = author.getFirstName().toLowerCase();
        String lastName = author.getLastName().toLowerCase();
        return authorRepository
          .findByFirstNameAndLastName(firstName, lastName)
          .orElseGet(() -> {
            Author createdAuthor = new Author(firstName, lastName);
            newAuthors.add(createdAuthor);
            return createdAuthor;
          });
      }).collect(Collectors.toSet());

    authorRepository.saveAll(newAuthors);

    var genres = genreRepository.findByNameIn(request.getGenre())
      .orElseThrow(() -> new NotFoundException("Genre not found"));

    Book book = new BookBuilder()
      .title(request.getTitle())
      .genres(genres)
      .authors(authors)
      .price(new Price(request.getPrice()))
      .build();

    book = bookRepository.save(book);
    inventoryRepository.save(new Inventory(request.getQty(), book));
  }

}
