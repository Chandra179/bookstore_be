package com.alexandria.books.domain.service.impl;

import com.alexandria.books.domain.repository.KeyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRepositoryImpl implements KeyValueRepository {
  private final RedisTemplate<String, Object> redisTemplate;

  @Autowired
  public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void save(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public Object get(String key) {
    return redisTemplate.opsForValue().get(key);
  }
}
