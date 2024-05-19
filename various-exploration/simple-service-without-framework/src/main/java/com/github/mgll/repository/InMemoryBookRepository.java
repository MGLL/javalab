package com.github.mgll.repository;

import com.github.mgll.model.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// it is actually a DAO
public class InMemoryBookRepository implements BookRepository {

  private static final Map<Long, Book> BOOKS_STORE = new ConcurrentHashMap<>();

  @Override
  public Book save(Book book) {
    if (book.getId() == null) {
      book.setId(getLastId());
    }
    BOOKS_STORE.put(book.getId(), book);
    return book;
  }

  private Long getLastId() {
    if (BOOKS_STORE.isEmpty()) return 1L;
    return Collections.max(BOOKS_STORE.keySet()) + 1L;
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
  }
}
