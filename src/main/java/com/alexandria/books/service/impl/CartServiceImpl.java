package com.alexandria.books.service.impl;

import com.alexandria.books.service.CartService;
import com.alexandria.books.exception.NotFoundException;
import com.alexandria.books.repository.KeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {

  KeyValueRepository KeyValueRepository;

  @Autowired
  public CartServiceImpl(RedisRepositoryImpl redisRepositoryImpl) {
    this.KeyValueRepository = redisRepositoryImpl;
  }

  @Override
  public Integer getItem(String key) {
    var value = KeyValueRepository.get(key);
    if (Objects.isNull(value)) {
      throw new NotFoundException("Item not found");
    }
    return (Integer) value;
  }

  @Override
  public void saveItem(String key, Object value) {
    KeyValueRepository.save(key, value);
  }

  @Override
  public void deleteItem(String key) {
    KeyValueRepository.delete(key);
  }
}
