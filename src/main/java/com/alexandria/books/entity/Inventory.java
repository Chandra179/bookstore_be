package com.alexandria.books.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Inventory implements Serializable {

  @Serial
  private static final long serialVersionUID = 1;

  @Id
  @Column(name = "fk_book")
  private UUID id;

  @Column
  private BigInteger qty;

  @OneToOne
  @MapsId
  private Book book;

  public Inventory(Book book, BigInteger qty) {
    this.book = book;
    this.qty = qty;
  }

  public Inventory(BigInteger qty) {
    this.qty = qty;
  }
}
