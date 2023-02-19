package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

  Page<Book> findAllBooks(int page, int size);

  List<Book> findBooksByQueryParam(String id, String title, Genre.GENRE genres);
}
