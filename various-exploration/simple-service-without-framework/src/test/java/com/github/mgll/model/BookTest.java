package com.github.mgll.model;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;

import com.github.mgll.dto.BookCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

  @Test
  void createBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    Assertions.assertEquals(book.getName(), BOOK_NAME);
    Assertions.assertEquals(book.getAuthorName(), AUTHOR_NAME);
  }

  @Test
  void createBookFromDto_shouldBeSuccessful() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    Book book = Book.fromBookCreate(dto);
    Assertions.assertEquals(book.getName(), BOOK_NAME);
    Assertions.assertEquals(book.getAuthorName(), AUTHOR_NAME);
  }

  @Test
  void createAndModifyBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book.setName(NEW_BOOK_NAME);
    book.setAuthorName(NEW_AUTHOR_NAME);
    Assertions.assertEquals(book.getName(), NEW_BOOK_NAME);
    Assertions.assertEquals(book.getAuthorName(), NEW_AUTHOR_NAME);
  }
}
