package com.github.mgll;

import com.github.mgll.repository.BookRepository;
import com.github.mgll.service.BookService;

public interface ApplicationBookContextSupport {

  default ApplicationBookContext context() {
    return ApplicationBookContext.getInstance();
  }

  default BookRepository repository() {
    return context().repository();
  }

  default BookService service() {
    return context().service();
  }
}
