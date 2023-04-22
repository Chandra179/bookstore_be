package com.alexandria.books.service;

public interface CartService {
  Object getItem(String key);
  void saveItem(String key, Object value);
  void deleteItem(String key);
}
