package com.github.mgll.exception;

import static com.github.mgll.Constants.BOOK_NOT_FOUND_EXCEPTION_MSG;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookNotFoundExceptionTest {

  @Test
  void createBookException_messageShouldMatch() {
    BookNotFoundException exception = new BookNotFoundException();
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }
}
