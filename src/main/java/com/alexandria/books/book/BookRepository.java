package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, CrudRepository<Book, UUID> {

  List<Book> findByGenresAndTitleContaining(Genre genre, String title);
  List<Book> findByTitleContaining(String title);
}
