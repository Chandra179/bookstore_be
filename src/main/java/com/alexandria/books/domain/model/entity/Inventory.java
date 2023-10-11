package com.alexandria.books.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Inventory {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private BigInteger qty;

  @OneToOne
  @JoinColumn(name = "book_id")
  private Book book;

  public Inventory(BigInteger qty, Book book) {
    this.book = book;
    this.qty = qty;
  }
}
