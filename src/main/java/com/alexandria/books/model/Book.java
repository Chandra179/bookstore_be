package com.alexandria.books.model;

import com.alexandria.books.valueobjects.Price;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Book {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String title;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "book_genre",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
  Set<Genre> genres;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "book_author",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
  Set<Author> authors;

  @Embedded
  private Price price;
}
