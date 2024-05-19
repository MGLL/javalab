package com.github.mgll.dto;

import com.github.mgll.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookResponse {
  private final Long id;
  private String name;
  private String authorName;

  public BookResponse(Book book) {
    this.id = book.getId();
    this.name = book.getName();
    this.authorName = book.getAuthorName();
  }

  public static List<BookResponse> fromBooks(List<Book> books) {
    List<BookResponse> responses = new ArrayList<>();
    for (Book b : books) {
      responses.add(new BookResponse(b));
    }
    return responses;
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
