package com.alexandria.books.dto;

import com.alexandria.books.entity.Author;
import com.alexandria.books.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Builder
public class CompleteBookResponse {

  @JsonProperty("title")
  String title;
  
  @JsonProperty("genres")
  @JsonIgnoreProperties(value = {"id"})
  HashSet<Genre> genres;

  @JsonProperty("authors")
  @JsonIgnoreProperties(value = {"id"})
  Set<Author> authors;

  @JsonProperty("qty")
  BigInteger qty;

  @JsonProperty("price")
  BigDecimal price;
}
