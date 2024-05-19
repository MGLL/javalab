package com.github.mgll;

import com.github.mgll.repository.InMemoryBookRepository;
import com.github.mgll.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationBookContextTest {

  @Test
  void validateApplicationContext() {
    ApplicationBookContext context = ApplicationBookContext.getInstance();
    Assertions.assertNotNull(context);
    Assertions.assertInstanceOf(BookServiceImpl.class, context.service());
    Assertions.assertInstanceOf(InMemoryBookRepository.class, context.repository());
  }
}
