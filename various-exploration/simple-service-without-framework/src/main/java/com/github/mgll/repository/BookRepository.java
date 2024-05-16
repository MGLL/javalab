package com.github.mgll.repository;

import com.github.mgll.model.Book;
import java.util.List;

public interface BookRepository {
  Book save(Book book);
  Book getBookById(Long id);
  List<Book> getBooks();
  void delete(Book book);
}
