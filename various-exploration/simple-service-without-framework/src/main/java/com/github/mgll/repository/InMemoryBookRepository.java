package com.github.mgll.repository;

import com.github.mgll.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBookRepository implements BookRepository {

  private static final Map<Long, Book> BOOKS_STORE = new ConcurrentHashMap<>();
  private Long lastId = 0L;

  @Override
  public Book save(Book book) {
    if (book.getId() == null) {
      lastId++;
      book.setId(lastId);
    }
    BOOKS_STORE.put(lastId, book);
    return book;
  }

  @Override
  public Book getBookById(Long id) {
    return BOOKS_STORE.get(id);
  }

  @Override
  public List<Book> getBooks() {
    return new ArrayList<>(BOOKS_STORE.values());
  }

  @Override
  public void delete(Book book) {
    BOOKS_STORE.remove(book.getId());
  }
}
