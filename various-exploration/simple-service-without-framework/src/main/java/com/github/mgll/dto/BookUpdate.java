package com.github.mgll.dto;

public class BookUpdate {
  private Long id;
  private String name;
  private String authorName;

  public BookUpdate() {}

  public BookUpdate(Long id, String name, String authorName) {
    this.id = id;
    this.name = name;
    this.authorName = authorName;
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
