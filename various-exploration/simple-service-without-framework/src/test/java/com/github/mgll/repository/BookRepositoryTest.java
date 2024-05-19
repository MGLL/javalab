package com.github.mgll.repository;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;

import com.github.mgll.ApplicationBookContextSupport;
import com.github.mgll.model.Book;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookRepositoryTest implements ApplicationBookContextSupport {

  @AfterEach
  public void clearRepository() {
    repository().teardownForTests();
    Assertions.assertTrue(repository().getBooks().isEmpty());
  }

  @Test
  void saveBook_shouldCreateBook() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book = repository().save(book);
    Assertions.assertNotNull(book.getId());
    Assertions.assertEquals(1L, book.getId());

    Optional<Book> optionalBook = repository().getBookById(book.getId());
    Assertions.assertTrue(optionalBook.isPresent());
    Book foundBook = optionalBook.get();
    Assertions.assertEquals(book.getName(), foundBook.getName());
    Assertions.assertEquals(book.getAuthorName(), foundBook.getAuthorName());
  }

  @Test
  void getNonExistingBook_optionalShouldBeEmpty() {
    Optional<Book> optionalBook = repository().getBookById(100L);
    Assertions.assertTrue(optionalBook.isEmpty());
  }

  @Test
  void getExistingBook_optionalShouldNotBeEmpty() {
    repository().save(new Book(BOOK_NAME, AUTHOR_NAME));
    Optional<Book> optionalBook = repository().getBookById(1L);
    Assertions.assertTrue(optionalBook.isPresent());
  }

  @Test
  void saveMultipleBook_shouldCreateDifferentBooks() {
    Book firstBook = new Book(BOOK_NAME, AUTHOR_NAME);
    firstBook = repository().save(firstBook);
    Assertions.assertNotNull(firstBook.getId());

    Book secondBook = new Book(BOOK_NAME, AUTHOR_NAME);
    secondBook = repository().save(secondBook);
    Assertions.assertNotNull(secondBook.getId());

    Assertions.assertNotEquals(firstBook.getId(), secondBook.getId());
    Assertions.assertEquals(2, repository().getBooks().size());
  }

  @Test
  void updateBook_shouldModifyBook() {
    Book book = repository().save(new Book(BOOK_NAME, AUTHOR_NAME));
    Assertions.assertNotNull(book.getId());

    Optional<Book> optionalBook = repository().getBookById(book.getId());
    Assertions.assertTrue(optionalBook.isPresent());

    Book foundBook = optionalBook.get();
    Assertions.assertEquals(book.getName(), foundBook.getName());
    Assertions.assertEquals(book.getAuthorName(), foundBook.getAuthorName());

    foundBook.setName(NEW_BOOK_NAME);
    foundBook.setAuthorName(NEW_AUTHOR_NAME);
    Book updatedBook = repository().save(foundBook);

    Assertions.assertNotNull(updatedBook);
    Assertions.assertEquals(book.getId(), updatedBook.getId());
    Assertions.assertEquals(foundBook.getId(), updatedBook.getId());
    Assertions.assertEquals(NEW_BOOK_NAME, updatedBook.getName());
    Assertions.assertEquals(NEW_AUTHOR_NAME, updatedBook.getAuthorName());
    Assertions.assertEquals(1, repository().getBooks().size());
  }

  @Test
  void deleteBook_shouldRemoveBook() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book = repository().save(book);
    Assertions.assertNotNull(book.getId());

    repository().delete(book);
    Assertions.assertTrue(repository().getBooks().isEmpty());
  }

  @Test
  void deleteNonExistingBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book.setId(10L);
    repository().delete(book);
    Assertions.assertTrue(repository().getBooks().isEmpty());
  }
}
