package com.alexandria.books.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BookCustomResponse {

  @JsonProperty("title")
  String title;

  @JsonProperty("author")
  String author;

  @JsonProperty("genres")
  String genres;

  public BookCustomResponse(String title, String author) {
    this.title = title;
    this.author = author;
  }
}
