package com.alexandria.books.service;

public interface CartService {
  Integer getItem(String key);
  void saveItem(String key, Object value);
  void deleteItem(String key);
}
