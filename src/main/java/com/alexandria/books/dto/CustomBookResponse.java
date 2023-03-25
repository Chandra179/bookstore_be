package com.alexandria.books.dto;

import lombok.Value;

import java.util.List;

@Value
public class CustomBookResponse {
  String title;
  List<CustomAuthorResponse> authors;
}
