package com.alexandria.books.factories;

import com.alexandria.books.model.Author;
import com.alexandria.books.model.Book;
import com.alexandria.books.model.Genre;
import com.alexandria.books.valueobjects.Price;

import java.util.HashSet;
import java.util.Set;

public class BookBuilder {
  private final Book book;
  private final Set<Author> authors;
  private final Set<Genre> genres;
  private Price price;

  public BookBuilder() {
    book = new Book();
    authors = new HashSet<>();
    genres = new HashSet<>();
  }

  public BookBuilder title(String title) {
    book.setTitle(title);
    return this;
  }

  public BookBuilder authors(Set<Author> authorsReq) {
    authors.addAll(authorsReq);
    return this;
  }

  public BookBuilder genres(Set<Genre> genres) {
    book.setGenres(genres);
    return this;
  }

  public BookBuilder price(Price price) {
    this.price = price;
    return this;
  }

  public Book build() {
    book.setAuthors(authors);
    book.setGenres(genres);
    book.setPrice(price);
    return book;
  }
}
