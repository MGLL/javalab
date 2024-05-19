package com.github.mgll.repository;

import com.github.mgll.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
  Book save(Book book);
  Optional<Book> getBookById(Long id);
  List<Book> getBooks();
  void delete(Book book);

  void teardownForTests();
}
