package com.alexandria.books.application.factories;

import com.alexandria.books.domain.model.entity.Author;
import com.alexandria.books.domain.model.entity.Book;
import com.alexandria.books.domain.model.entity.Genre;
import com.alexandria.books.domain.model.entity.Inventory;
import com.alexandria.books.domain.model.entity.Pricing;

import java.util.HashSet;
import java.util.Set;

public class BookBuilder {
  private final Book book;
  private final Set<Author> authors;
  private final Set<Genre> genres;
  private Inventory inventory;
  private Pricing pricing;

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

  public BookBuilder inventory(Inventory inventory) {
    inventory.setBook(book);
    this.inventory = inventory;
    return this;
  }

  public BookBuilder pricing(Pricing pricing) {
    pricing.setBook(book);
    this.pricing = pricing;
    return this;
  }

  public Book build() {
    book.setAuthors(authors);
    book.setGenres(genres);
    book.setInventory(inventory);
    book.setPricing(pricing);
    return book;
  }
}