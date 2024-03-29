package com.alexandria.books.service.impl;

import com.alexandria.books.model.Genre;
import com.alexandria.books.service.GenreService;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

  GenreRepository genreRepository;

  @Autowired
  public GenreServiceImpl(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Override
  public List<Genre> getAllGenre() {
    Sort sort = Sort.by(Sort.Direction.ASC, "name");
    var genres = genreRepository.findAll(sort).stream().toList();
    if (genres.isEmpty()) throw new NotFoundException("Genre not found");
    return genres;
  }
}
