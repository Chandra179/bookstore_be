package com.alexandria.books.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse<T> {

  public static <T> BaseResponse<T> build(T data){
    return new BaseResponse<>(data);
  }

  @JsonProperty("data")
  private T data;

  public BaseResponse(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
