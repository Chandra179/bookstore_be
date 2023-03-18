package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import com.alexandria.books.book.genre.GenreRepository;
import com.alexandria.books.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final GenreRepository genreRepository;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
    this.bookRepository = bookRepository;
    this.genreRepository = genreRepository;
  }

  @Override
  public List<BookPaginationResponse> findAllBooks(int page, int size) {
    var bookList = bookRepository.findAllWithPagination(PageRequest.of(page, size));
    if (bookList.isEmpty()) throw new NotFoundException("Book not found");
    return bookList;
  }

  @Override
  public List<Book> findBooksByQueryParam(String id, String title, Genre.GENRE genre) {
    if (!title.isEmpty() && genre != null) {
      var aGenre = genreRepository.findByName(genre)
        .orElseThrow(() -> new NotFoundException("Genre not found"));
      return bookRepository.findByGenresAndTitleContaining(aGenre, title);
    }

    if (!title.isEmpty()) {
      return bookRepository.findByTitleContaining(title);
    }

    if (!id.isEmpty()) {
      return List.of(bookRepository
        .findById(UUID.fromString(id))
        .orElseThrow(() -> new NotFoundException("Book not found")));
    }
    return Collections.emptyList();
  }

}
