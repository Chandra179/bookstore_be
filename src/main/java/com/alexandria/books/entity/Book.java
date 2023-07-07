package com.alexandria.books.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
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

  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Inventory inventory;

  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Pricing pricing;
}
