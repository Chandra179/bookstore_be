package com.alexandria.books.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueRepository {
  void save(String key, Object value);
  void delete(String key);
  Object get(String key);
}
