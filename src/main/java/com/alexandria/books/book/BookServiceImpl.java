package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import com.alexandria.books.book.genre.GenreRepository;
import com.alexandria.books.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
  public List<CustomBookResponse> findAllBooks(int page, int size) {
    var books = bookRepository.findAll(PageRequest.of(page, size));
    if (books.isEmpty()) throw new NotFoundException("Book not found");
    List<CustomBookResponse> responses = new ArrayList<>();
    books.forEach(book -> {
      CustomBookResponse response = new CustomBookResponse(book.getTitle(), new ArrayList<>());
      book.getAuthors().forEach(
        author -> response.getAuthors().add(new AuthorDTO(author.getFirstName(), author.getLastName()))
      );
      responses.add(response);
    });
    return responses;
  }

  @Override
  public List<Book> findBooksByQueryParam(String id, String title, Genre.GENRE genre) {
    if (!title.isEmpty() && genre != null) {
      var aGenre = genreRepository.findByName(genre)
        .orElseThrow(() -> new NotFoundException("Genre not found"));
      return bookRepository.findByGenresAndTitleContaining(aGenre, title);
    }

    if (!title.isEmpty()) {
      System.out.println(title);
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
