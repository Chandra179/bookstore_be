package com.alexandria.books.service;

import com.alexandria.books.dto.CustomBookResponse;
import com.alexandria.books.entity.Genre;

import java.util.List;

public interface BookService {

  List<CustomBookResponse> findBooksByPage(int page, int size);

  List<CustomBookResponse> findBooksByRequestParam(String title, Genre.GENRE genre);
}
