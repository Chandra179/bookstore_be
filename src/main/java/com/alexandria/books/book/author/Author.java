package com.alexandria.books.book.author;

import com.alexandria.books.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@JsonIgnoreProperties("books")
public class Author implements Serializable {

  @Serial
  private static final long serialVersionUID = 1;

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
  Set<Book> books;

}
