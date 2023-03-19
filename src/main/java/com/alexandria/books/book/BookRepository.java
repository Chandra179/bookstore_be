package com.alexandria.books.book;

import com.alexandria.books.book.genre.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, CrudRepository<Book, UUID> {

  List<Book> findByGenresAndTitleContaining(Genre genre, String title);
  List<Book> findByTitleContaining(String title);

  @Query("SELECT b.title AS title, a FROM Book b JOIN b.authors a")
  List<Book> findAllWithPagination(Pageable pageable);
}
