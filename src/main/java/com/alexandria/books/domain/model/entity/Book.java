package com.alexandria.books.domain.model.entity;

import com.alexandria.books.domain.model.valueobjects.Price;
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
    joinColumns = @JoinColumn(name = "fk_book"),
    inverseJoinColumns = @JoinColumn(name = "fk_genre"))
  Set<Genre> genres;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "book_author",
    joinColumns = @JoinColumn(name = "fk_book"),
    inverseJoinColumns = @JoinColumn(name = "fk_author"))
  Set<Author> authors;

  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
  private Inventory inventory;

  @Embedded
  private Price price;
}
