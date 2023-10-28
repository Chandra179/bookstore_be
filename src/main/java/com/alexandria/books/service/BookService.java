package com.alexandria.books.service;

import com.alexandria.books.dto.BookApiRequest;
import com.alexandria.books.dto.BookApiResponse;
import com.alexandria.books.model.Genre;

import java.util.List;

public interface BookService {
  List<BookApiResponse> findBooks(int page, int size);
  List<BookApiResponse> findBooks(String title, Genre.GENRE genre);
  void createBook(BookApiRequest request);
}
