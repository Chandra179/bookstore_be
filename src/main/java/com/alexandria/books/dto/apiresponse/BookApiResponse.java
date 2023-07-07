package com.alexandria.books.dto.apiresponse;

import com.alexandria.books.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class BookApiResponse {
  String title;
  List<AuthorDto> authors;
}
