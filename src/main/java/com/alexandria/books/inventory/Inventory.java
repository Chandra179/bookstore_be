package com.alexandria.books.inventory;

import com.alexandria.books.book.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
  @Column(name = "book_id")
  private UUID bookId;

  @Column
  private BigInteger qty;

  @OneToOne
  @MapsId
  @JoinColumn(name = "book_id")
  private Book book;

}
