package com.alexandria.books.book;

import com.alexandria.books.model.Author;
import com.alexandria.books.model.Book;
import com.alexandria.books.repository.AuthorRepository;
import com.alexandria.books.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BookRepositoryTest {

  @Autowired
  BookRepository bookRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Test
  void insertBook() {
    var savedBook = bookRepository.save(Book.builder().title("title1").build());
    var book = bookRepository.findById(savedBook.getId()).orElse(null);
    assertNotNull(book);
    Assertions.assertEquals(savedBook.getTitle(), book.getTitle());
  }

  @Test
  void getAllBooks() {
    var savedAuthor = authorRepository.save(
      Author.builder().firstName("jackie").lastName("chan").build()
    );
    var savedBook = bookRepository.save(
      Book.builder().title("title1").authors(Set.of(savedAuthor)).build()
    );
    var author = authorRepository.findById(savedAuthor.getId()).orElse(null);
    var book = bookRepository.findById(savedBook.getId()).orElse(null);

    assertNotNull(author);
    assertNotNull(book);
    Assertions.assertEquals(savedAuthor.getId(), author.getId());
    Assertions.assertEquals(savedBook.getId(), book.getId());
  }

  @Test
  void deleteBook() {
    var savedBook = bookRepository.save(Book.builder().title("title1").build());
    bookRepository.delete(savedBook);
    var book = bookRepository.findById(savedBook.getId()).orElse(null);
    assertNull(book);
  }

  @Test
  void updateBook() {
    var savedBook = bookRepository.save(Book.builder().title("title1").build());
    savedBook.setTitle("title2");
    var book = bookRepository.findById(savedBook.getId()).orElse(null);
    assertNotNull(book);
    Assertions.assertEquals(savedBook.getId(), book.getId());
    Assertions.assertEquals(savedBook.getTitle(), book.getTitle());
  }
}
