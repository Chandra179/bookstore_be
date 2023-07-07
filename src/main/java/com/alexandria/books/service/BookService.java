package com.alexandria.books.service;

import com.alexandria.books.dto.apirequest.BookApiRequest;
import com.alexandria.books.dto.apiresponse.BookApiResponse;
import com.alexandria.books.entity.Genre;

import java.util.List;

public interface BookService {
  List<BookApiResponse> findBooksByPage(int page, int size);
  List<BookApiResponse> findBooksByRequestParam(String title, Genre.GENRE genre);
  void createBook(BookApiRequest request);
}
