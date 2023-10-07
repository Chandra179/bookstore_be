package com.alexandria.books.domain.service;

public interface CartService {
  Integer getItem(String key);
  void saveItem(String key, Object value);
  void deleteItem(String key);
}
