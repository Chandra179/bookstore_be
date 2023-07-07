package com.alexandria.books.dto.apirequest;

import com.alexandria.books.dto.AuthorDto;
import com.alexandria.books.entity.Genre;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Getter
public class BookApiRequest {
  String title;
  List<AuthorDto> authors;
  Set<Genre.GENRE> genre;
  // min 0, max ?
  BigInteger qty;
  // min 0, max ?,
  BigDecimal price;
}
