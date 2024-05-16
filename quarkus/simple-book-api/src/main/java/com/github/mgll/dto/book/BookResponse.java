package com.github.mgll.dto.book;

import com.github.mgll.data.entity.Book;
import java.util.ArrayList;
import java.util.List;

public class BookResponse {
  String id;
  String name;

  public BookResponse() {
  }

  public static BookResponse fromBook(Book book) {
    BookResponse response = new BookResponse();
    response.id = book.id;
    response.name = book.name;
    return response;
  }

  public static List<BookResponse> fromBooks(List<Book> books) {
    List<BookResponse> response = new ArrayList<>();
    books.forEach(b -> response.add(fromBook(b)));
    return response;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
