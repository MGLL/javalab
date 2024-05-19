package com.github.mgll.dto;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookCreateTest {

  @Test
  void bookCreateDto_shouldBeSuccessful() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    Assertions.assertNotNull(dto);
    Assertions.assertEquals(BOOK_NAME, dto.getName());
    Assertions.assertEquals(AUTHOR_NAME, dto.getAuthorName());
  }

  @Test
  void bookCreateDto_allMethodsShouldBeSuccessful() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    Assertions.assertNotNull(dto);
    dto.setName(NEW_BOOK_NAME);
    Assertions.assertEquals(NEW_BOOK_NAME, dto.getName());
    dto.setAuthorName(NEW_AUTHOR_NAME);
    Assertions.assertEquals(NEW_AUTHOR_NAME, dto.getAuthorName());
  }
}
