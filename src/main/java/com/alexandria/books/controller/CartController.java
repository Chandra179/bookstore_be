package com.alexandria.books.controller;

import com.alexandria.books.service.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart")
@RequestMapping("/cart")
@RestController
public class CartController {

  @Autowired
  CartServiceImpl cartService;

  @Operation(description = "Save cart item")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
  public void saveItem() {
    cartService.saveItem(null, null);
  }

  @Operation(description = "Get cart item")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"),
    @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public void getItem() {
    cartService.getItem(null);
  }

  @Operation(description = "Delete cart item")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
  @DeleteMapping
  public void deleteItem() {
    cartService.deleteItem(null);
  }
}
