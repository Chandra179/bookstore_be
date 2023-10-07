package com.alexandria.books.domain.repository;

import com.alexandria.books.domain.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID>, CrudRepository<Genre, UUID> {

  Optional<Set<Genre>> findByNameIn(Set<Genre.GENRE> genres);
}