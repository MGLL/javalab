package com.github.mgll.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

  @Test
  void createBook() {
    String bookName = "First book";
    String authorName = "T.";
    Book book = new Book(bookName, authorName);
    Assertions.assertEquals(book.getName(), bookName);
    Assertions.assertEquals(book.getAuthorName(), authorName);
  }

  @Test
  void createAndModifyBook() {
    Book book = new Book("First book", "T.");
    String newBookName = "Alternative";
    String newAuthorName = "V.";
    book.setName(newBookName);
    book.setAuthorName(newAuthorName);
    Assertions.assertEquals(book.getName(), newBookName);
    Assertions.assertEquals(book.getAuthorName(), newAuthorName);
  }

}
