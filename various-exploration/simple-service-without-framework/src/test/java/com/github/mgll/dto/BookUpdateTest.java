package com.github.mgll.dto;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookUpdateTest {

  @Test
  void bookUpdateDto_shouldBeSuccessful() {
    Long id = 1L;
    BookUpdate dto = new BookUpdate(id, BOOK_NAME, AUTHOR_NAME);
    Assertions.assertNotNull(dto);
    Assertions.assertEquals(id, dto.getId());
    Assertions.assertEquals(BOOK_NAME, dto.getName());
    Assertions.assertEquals(AUTHOR_NAME, dto.getAuthorName());
  }

  @Test
  void bookUpdateDto_allMethodsShouldBeSuccessful() {
    Long id = 10L;
    BookUpdate dto = new BookUpdate();
    dto.setId(id);
    Assertions.assertEquals(id, dto.getId());
    dto.setName(BOOK_NAME);
    Assertions.assertEquals(BOOK_NAME, dto.getName());
    dto.setAuthorName(AUTHOR_NAME);
    Assertions.assertEquals(AUTHOR_NAME, dto.getAuthorName());
  }
}
