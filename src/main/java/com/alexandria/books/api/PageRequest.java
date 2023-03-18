package com.alexandria.books.api;

public class PageRequest {
  private int pageNumber;
  private int pageSize;
  private String sortOrder;

  public PageRequest(int pageNumber, int pageSize, String sortOrder) {
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.sortOrder = sortOrder;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public String getSortOrder() {
    return sortOrder;
  }
}
