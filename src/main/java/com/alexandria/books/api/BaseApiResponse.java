package com.alexandria.books.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseApiResponse<T> {

  public static <T> BaseApiResponse<T> build(T data){
    return new BaseApiResponse<>(data);
  }

  @JsonProperty("data")
  private T data;

  public BaseApiResponse(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
}