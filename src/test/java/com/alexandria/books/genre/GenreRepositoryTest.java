package com.alexandria.books.genre;

import com.alexandria.books.model.Genre;
import com.alexandria.books.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class GenreRepositoryTest {

  @Autowired
  GenreRepository genreRepository;

  @Test
  void getAllGenre() {
    var genres = genreRepository.findAll();
    assertFalse(genres.isEmpty());
    genres.forEach((x) -> Assertions.assertEquals(x.getName(), Genre.GENRE.valueOf(x.getName().name())));
  }

}
