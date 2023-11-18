package com.alexandria.books.controller.v1;

import com.alexandria.books.dto.BaseApiResponse;
import com.alexandria.books.model.Genre;
import com.alexandria.books.service.impl.GenreServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Genre")
@RequestMapping("/v1/genre")
@RestController
public class GenreController {

  @Autowired
  GenreServiceImpl genreService;

  @Operation(description = "Genre list")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Success"),
      @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseApiResponse<List<Genre>> getAllGenre() {
    return BaseApiResponse.build(genreService.getAllGenre());
  }

}
