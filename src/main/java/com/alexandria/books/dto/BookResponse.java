package com.alexandria.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class BookResponse {
  String title;
  List<AuthorResponse> authors;
}
