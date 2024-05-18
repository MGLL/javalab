package com.github.mgll.dto;

import com.github.mgll.model.Book;

public class BookResponse {
  private final Long id;
  private String name;
  private String authorName;

  public BookResponse(Book book) {
    this.id = book.getId();
    this.name = book.getName();
    this.authorName = book.getAuthorName();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
}
