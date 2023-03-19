package com.alexandria.books.book;

import com.alexandria.books.author.AuthorDTO;
import lombok.Value;

import java.util.List;

@Value
public class CustomBookResponse {
  String title;
  List<AuthorDTO> authors;
}
