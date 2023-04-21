package com.alexandria.books.service;

import com.alexandria.books.dto.CreateBookRequest;
import com.alexandria.books.dto.BookResponse;
import com.alexandria.books.entity.Genre;

import java.util.List;

public interface BookService {
  List<BookResponse> findBooksByPage(int page, int size);
  List<BookResponse> findBooksByRequestParam(String title, Genre.GENRE genre);
  void createBook(CreateBookRequest request);
}
