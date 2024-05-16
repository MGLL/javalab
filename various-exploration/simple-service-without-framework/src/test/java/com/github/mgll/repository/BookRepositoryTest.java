package com.github.mgll.repository;

import com.github.mgll.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookRepositoryTest {

  @Test
  void saveBook() {
    BookRepository repository = new InMemoryBookRepository();

    Book book = new Book("A", "T.");
    book = repository.save(book);
    Assertions.assertNotNull(book.getId());

    Book foundBook = repository.getBookById(book.getId());
    Assertions.assertNotNull(foundBook);
    Assertions.assertEquals(foundBook.getName(), book.getName());
    Assertions.assertEquals(foundBook.getAuthorName(), book.getAuthorName());
  }

  @Test
  void saveMultipleBook() {
    BookRepository repository = new InMemoryBookRepository();

    Book firstBook = new Book("A", "T.");
    firstBook = repository.save(firstBook);
    Assertions.assertNotNull(firstBook.getId());

    Book secondBook = new Book("A", "T.");
    secondBook = repository.save(secondBook);
    Assertions.assertNotNull(secondBook.getId());

    Assertions.assertNotEquals(firstBook.getId(), secondBook.getId());
    Assertions.assertEquals(repository.getBooks().size(), 2);
  }

  @Test
  void updateBook() {
    BookRepository repository = new InMemoryBookRepository();

    Book book = new Book("B", "T.");
    book = repository.save(book);
    Assertions.assertNotNull(book.getId());

    Book foundBook = repository.getBookById(book.getId());
    Assertions.assertNotNull(foundBook);
    Assertions.assertEquals(foundBook.getName(), book.getName());
    Assertions.assertEquals(foundBook.getAuthorName(), book.getAuthorName());

    String newBookName = "New B";
    String newAuthorName = "V.";
    foundBook.setName(newBookName);
    foundBook.setAuthorName(newAuthorName);
    book = repository.save(book);

    Assertions.assertNotNull(book);
    Assertions.assertEquals(book.getId(), foundBook.getId());
    Assertions.assertEquals(book.getName(), newBookName);
    Assertions.assertEquals(book.getAuthorName(), newAuthorName);
    Assertions.assertEquals(repository.getBooks().size(), 1);
  }

  @Test
  void deleteBook() {
    BookRepository repository = new InMemoryBookRepository();

    Book book = new Book("B", "T.");
    book = repository.save(book);
    Assertions.assertNotNull(book.getId());

    repository.delete(book);
    Assertions.assertEquals(repository.getBooks().size(), 0);
  }

  @Test
  void deleteNonExistingBook() {
    BookRepository repository = new InMemoryBookRepository();
    Book book = new Book("B", "T.");
    book.setId(10L);
    repository.delete(book);
    Assertions.assertEquals(repository.getBooks().size(), 0);
  }
}
