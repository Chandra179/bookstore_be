package com.alexandria.books.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Price {
  private BigDecimal amount;
}
