package com.github.mgll.repository;

import com.github.mgll.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
  public Optional<Book> getBookById(Long id) {
    Book b = BOOKS_STORE.get(id);
    return b == null ? Optional.empty() : Optional.of(b);
  }

  @Override
  public List<Book> getBooks() {
    return new ArrayList<>(BOOKS_STORE.values());
  }

  @Override
  public void delete(Book book) {
    BOOKS_STORE.remove(book.getId());
  }

  // TODO: Think of a security, maybe check profile?
  @Override
  public void teardownForTests() {
    for (Long id : BOOKS_STORE.keySet()) {
      BOOKS_STORE.remove(id);
    }
    lastId = 0L;
  }
}
