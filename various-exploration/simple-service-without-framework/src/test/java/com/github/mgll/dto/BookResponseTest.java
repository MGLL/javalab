package com.github.mgll.dto;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;

import com.github.mgll.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookResponseTest {

  @Test
  void responseBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    BookResponse response = new BookResponse(book);
    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getName(), BOOK_NAME);
    Assertions.assertEquals(response.getAuthorName(), AUTHOR_NAME);
  }
}
