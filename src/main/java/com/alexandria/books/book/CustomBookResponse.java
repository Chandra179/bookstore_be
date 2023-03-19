package com.alexandria.books.book;

import lombok.Value;

import java.util.List;

@Value
public class CustomBookResponse {
  String title;
  List<AuthorDTO> authors;
}
