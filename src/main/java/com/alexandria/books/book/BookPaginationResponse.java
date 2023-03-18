package com.alexandria.books.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BookPaginationResponse {

  @JsonProperty("title")
  String title;

  @JsonProperty("author")
  String author;
}
