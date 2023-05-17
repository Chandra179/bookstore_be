package com.alexandria.books.builder;

import com.alexandria.books.entity.Author;
import com.alexandria.books.entity.Book;
import com.alexandria.books.entity.Genre;
import com.alexandria.books.entity.Inventory;
import com.alexandria.books.entity.Pricing;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class BookBuilder {
  private final Book book;
  private final Set<Author> authors;
  private final Set<Genre> genres;
  private BigInteger qty;
  private BigDecimal price;

  public BookBuilder() {
    book = new Book();
    authors = new HashSet<>();
    genres = new HashSet<>();
  }

  public BookBuilder setTitle(String title) {
    book.setTitle(title);
    return this;
  }

  public BookBuilder setAuthors(Set<Author> authorsReq) {
    authors.addAll(authorsReq);
    return this;
  }

  public BookBuilder setGenres(Set<Genre> genres) {
    book.setGenres(genres);
    return this;
  }

  public BookBuilder setQty(BigInteger qty) {
     this.qty = qty;
     return this;
  }

  public BookBuilder setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public Book build() {
    book.setAuthors(authors);
    book.setGenres(genres);
    book.setInventory(new Inventory(book, qty));
    book.setPricing(new Pricing(book, price));
    return book;
  }
}
