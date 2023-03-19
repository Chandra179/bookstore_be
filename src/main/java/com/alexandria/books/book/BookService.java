package com.alexandria.books.book;

import com.alexandria.books.genre.Genre;

import java.util.List;

public interface BookService {

  List<CustomBookResponse> findAllBooks(int page, int size);

  List<Book> findBooksByQueryParam(String id, String title, Genre.GENRE genres);
}
