package com.github.mgll.data.repository;

import com.github.mgll.data.entity.Book;
import java.util.List;

public interface BookRepository {
  List<Book> getBooks();
  Book getBook(String id);
}
