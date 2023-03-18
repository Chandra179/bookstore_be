package com.alexandria.books.book.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@JsonIgnoreProperties("books")
public class Author {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String firstName;

  @Column
  private String lastName;

}
