package com.github.mgll.model;

import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookUpdate;

public class Book {

  private Long id;
  private String name;
  private String authorName;

  public Book(String name, String authorName) {
    this.name = name;
    this.authorName = authorName;
  }

  public static Book fromBookCreate(BookCreate dto) {
    return new Book(dto.getName(), dto.getAuthorName());
  }

  public static Book fromBookUpdate(BookUpdate dto) {
    Book book = new Book(dto.getName(), dto.getAuthorName());
    book.setId(dto.getId());
    return book;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
