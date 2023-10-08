package com.alexandria.books.domain.model.entity;

import jakarta.persistence.*;
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
  @GeneratedValue
  private UUID id;

  @Column
  private BigInteger qty;

  @OneToOne
  @MapsId
  private Book book;

  public Inventory(BigInteger qty) {
    this.qty = qty;
  }
}
