package com.github.mgll.dto;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookCreateTest {

  @Test
  void createBook_shouldBeSuccessful() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    Assertions.assertNotNull(dto);
    Assertions.assertEquals(dto.getName(), BOOK_NAME);
    Assertions.assertEquals(dto.getAuthorName(), AUTHOR_NAME);
  }
}
