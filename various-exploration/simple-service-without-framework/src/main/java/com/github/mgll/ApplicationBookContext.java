package com.github.mgll;

import com.github.mgll.repository.BookRepository;
import com.github.mgll.repository.InMemoryBookRepository;
import com.github.mgll.service.BookService;
import com.github.mgll.service.BookServiceImpl;

public final class ApplicationBookContext {
  private static ApplicationBookContext context;
  private final BookService service = new BookServiceImpl();

  public static ApplicationBookContext getInstance() {
    if (context == null) {
      context = new ApplicationBookContext();
    }
    return context;
  }

  public BookService service() {
    return service;
  }

  public BookRepository repository() {
    return new InMemoryBookRepository();
  }
}
