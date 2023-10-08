package com.alexandria.books.application.service;

import com.alexandria.books.dto.BookApiRequest;
import com.alexandria.books.dto.BookApiResponse;
import com.alexandria.books.domain.model.entity.Genre;

import java.util.List;

public interface BookService {
  List<BookApiResponse> findBooks(int page, int size);
  List<BookApiResponse> findBooks(String title, Genre.GENRE genre);
  void createBook(BookApiRequest request);
}
