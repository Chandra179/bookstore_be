package com.alexandria.books.service;

import com.alexandria.books.repository.KeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

  KeyValueRepository KeyValueRepository;

  @Autowired
  public CartServiceImpl(RedisRepositoryImpl redisRepositoryImpl) {
    this.KeyValueRepository = redisRepositoryImpl;
  }

  @Override
  public Object getItem(String key) {
    return KeyValueRepository.get(key);
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
