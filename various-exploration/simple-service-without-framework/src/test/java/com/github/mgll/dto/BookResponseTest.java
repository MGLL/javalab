package com.github.mgll.dto;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;

import com.github.mgll.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookResponseTest {

  @Test
  void bookResponseDto_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    BookResponse response = new BookResponse(book);
    Assertions.assertNotNull(response);
    Assertions.assertEquals(BOOK_NAME, response.getName());
    Assertions.assertEquals(AUTHOR_NAME, response.getAuthorName());
  }

  @Test
  void bookResponseDto_allMethodsShouldBeSuccessful() {
    BookResponse response = new BookResponse(new Book(BOOK_NAME, AUTHOR_NAME));
    Assertions.assertNotNull(response);
    response.setName(NEW_BOOK_NAME);
    Assertions.assertEquals(NEW_BOOK_NAME, response.getName());
    response.setAuthorName(NEW_AUTHOR_NAME);
    Assertions.assertEquals(NEW_AUTHOR_NAME, response.getAuthorName());
  }
}
