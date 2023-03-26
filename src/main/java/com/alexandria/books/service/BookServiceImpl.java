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
import java.util.stream.Collectors;

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
  public List<CustomBookResponse> findBooksByQueryParam(String id, String title, Genre.GENRE genre) {
    List<CustomBookResponse> responses = new ArrayList<>();
    List<Book> books;

    if (!id.isEmpty()) {
      books = List.of(bookRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundException("Book not found")));
    } else if (!title.isEmpty() && genre != null) {
      Genre aGenre = genreRepository.findByName(genre)
        .orElseThrow(() -> new NotFoundException("Genre not found"));
      books = bookRepository.findByGenresAndTitleContaining(aGenre, title);
    } else if (genre != null) {
      Genre aGenre = genreRepository.findByName(genre)
        .orElseThrow(() -> new NotFoundException("Genre not found"));
      books = bookRepository.findByGenres(aGenre);
    } else if (!title.isEmpty()) {
      books = bookRepository.findByTitleContaining(title);
    } else {
      books = new ArrayList<>();
    }

    for (Book book : books) {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(
        author -> response.getAuthors().add(new CustomAuthorResponse(author.getFirstName(), author.getLastName()))
      );
      responses.add(response);
    }
    return responses;
  }

}
