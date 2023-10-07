package com.alexandria.books.domain.repository;

import com.alexandria.books.domain.model.entity.Book;
import com.alexandria.books.domain.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, CrudRepository<Book, UUID> {
  @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genreName AND b.title LIKE %:title%")
  List<Book> findByGenreNameAndTitleContaining(
    @Param("genreName") Genre.GENRE genreName,
    @Param("title") String title
  );
  List<Book> findByTitleContaining(String title);
  @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genreName")
  List<Book> findByGenreName(@Param("genreName") Genre.GENRE genreName);

}
