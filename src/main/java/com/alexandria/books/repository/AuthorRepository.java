package com.alexandria.books.repository;

import com.alexandria.books.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>, CrudRepository<Author, UUID> {
  Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
