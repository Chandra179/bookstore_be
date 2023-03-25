package com.alexandria.books.service;

import com.alexandria.books.dto.CustomBookResponse;
import com.alexandria.books.entity.Genre;

import java.util.List;

public interface BookService {

  List<CustomBookResponse> findAllBooks(int page, int size);

  List<CustomBookResponse> findBooksByQueryParam(String id, String title, Genre.GENRE genres);
}
