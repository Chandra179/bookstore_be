package com.alexandria.books.book.genre;

import com.alexandria.books.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GerneService {

  GenreRepository genreRepository;

  @Autowired
  public GenreServiceImpl(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Override
  public List<Genre> getAllGenre() {
    Sort sort = Sort.by(Sort.Direction.ASC, "name");
    var genres = genreRepository.findAll(sort).stream().toList();
    if (genres.isEmpty()) {
      throw new NotFoundException("Book not found");
    }
    return genreRepository.findAll(sort).stream().toList();
  }
}
