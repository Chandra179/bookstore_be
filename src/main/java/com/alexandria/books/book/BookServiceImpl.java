package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import com.alexandria.books.book.genre.GenreRepository;
import com.alexandria.books.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public Page<Book> findAllBooks(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Book> bookList = bookRepository.findAll(pageable);
    if (bookList.isEmpty()) {
      throw new NotFoundException(
        String.format("Book not found, last page was %s", Math.max(bookList.getTotalPages()-1, 0))
      );
    }
    return bookList;
  }

  @Override
  public List<Book> findBooksByQueryParam(String id, String title, Genre.GENRE genre) {
    /*
    * Book can have many genre, but we only need to filter it per Genre
    * Because in Frontend we will be just showing books per genre in a page
    * */
    if (!title.isEmpty() && genre != null) {
      var aGenre = genreRepository.findByName(genre)
        .orElseThrow(() -> new NotFoundException("Genre not found"));
      return bookRepository.findByGenresAndTitleContaining(aGenre, title);
    }

    /* maybe user/seller will be input the same title */
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
