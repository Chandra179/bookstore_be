package com.alexandria.books.service;

import com.alexandria.books.dto.CustomAuthorResponse;
import com.alexandria.books.dto.CustomBookResponse;
import com.alexandria.books.entity.Book;
import com.alexandria.books.entity.Genre;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.repository.BookRepository;
import com.alexandria.books.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final GenreRepository genreRepository;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
    this.bookRepository = bookRepository;
    this.genreRepository = genreRepository;
  }

  @Override
  public List<CustomBookResponse> findAllBooks(int page, int size) {
    var books = bookRepository.findAll(PageRequest.of(page, size));
    if (books.isEmpty()) throw new NotFoundException("Book not found");
    List<CustomBookResponse> responses = new ArrayList<>();
    books.forEach(book -> {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(
        author -> response.getAuthors().add(new CustomAuthorResponse(author.getFirstName(), author.getLastName()))
      );
      responses.add(response);
    });
    return responses;
  }

  @Override
  public List<CustomBookResponse> findBooksByQueryParam(String id, String title, Genre.GENRE genre) {
    List<CustomBookResponse> responses = new ArrayList<>();
    List<Book> books = new ArrayList<>();

    if (genre != null) {
      books = bookRepository.findByTitleContaining(title);
    }
    if (!title.isEmpty() && genre != null) {
      var aGenre = genreRepository.findByName(genre)
        .orElseThrow(() -> new NotFoundException("Genre not found"));
      books = bookRepository.findByGenresAndTitleContaining(aGenre, title);
    }
    if (!id.isEmpty()) {
      books = List.of(bookRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundException("Book not found")));
    }
    if (!title.isEmpty()) {
      books = bookRepository.findByTitleContaining(title);
    }
    books.forEach(book -> {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(
        author -> response.getAuthors().add(new CustomAuthorResponse(author.getFirstName(), author.getLastName()))
      );
      responses.add(response);
    });
    return responses;
  }

}
