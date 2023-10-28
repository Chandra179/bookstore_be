package com.alexandria.books.controller;

import com.alexandria.books.dto.BaseApiResponse;
import com.alexandria.books.service.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.alexandria.books.constants.RegexPattern.UUID_PATTERN;

@Tag(name = "Cart")
@RequestMapping("/cart")
@Validated
@RestController
public class CartController {

  @Autowired
  CartServiceImpl cartService;

  @Operation(description = "Save cart item")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
  @PostMapping
  public void saveItem(
    @RequestParam("id") @Pattern(regexp=UUID_PATTERN) String id,
    @RequestParam("qty") Integer qty
  ) {
    cartService.saveItem(id, qty);
  }

  @Operation(description = "Get cart item")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"),
    @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseApiResponse<Integer> getItem(@RequestParam("id") @Pattern(regexp=UUID_PATTERN) String id) {
    return BaseApiResponse.build(cartService.getItem(id));
  }

  @Operation(description = "Delete cart item")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
  @DeleteMapping
  public void deleteItem(@RequestParam("id") @Pattern(regexp=UUID_PATTERN) String id) {
    cartService.deleteItem(id);
  }
}
