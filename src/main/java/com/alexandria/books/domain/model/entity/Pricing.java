package com.alexandria.books.domain.model.entity;

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
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pricing")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Pricing implements Serializable {

  @Serial
  private static final long serialVersionUID = 1;

  @Id
  @Column(name = "fk_book")
  private UUID id;

  @Column
  private BigDecimal price;

  @OneToOne
  @MapsId
  private Book book;

  public Pricing(Book book, BigDecimal price) {
    this.book = book;
    this.price = price;
  }

  public Pricing(BigDecimal price) {
    this.price = price;
  }
}
