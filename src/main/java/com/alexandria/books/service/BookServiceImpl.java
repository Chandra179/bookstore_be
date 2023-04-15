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
      books = new ArrayList<>();
    }

    return books.stream().map(book -> {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(
        author -> response.getAuthors().add(new CustomAuthorResponse(author.getFirstName(), author.getLastName()))
      );
      return response;
    }).collect(Collectors.toList());
  }

}
