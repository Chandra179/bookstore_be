package com.alexandria.books.dto;

import com.alexandria.books.entity.Genre;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Getter
public class CreateBookRequest {
  String title;
  List<CreateAuthorRequest> authors;
  Set<Genre.GENRE> genre;
  // min 0, max ?
  BigInteger qty;
  // min 0, max ?,
  BigDecimal price;
}
