package com.github.mgll.repository;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;

import com.github.mgll.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookRepositoryTest {

  private final BookRepository repository = new InMemoryBookRepository();

  @AfterEach
  public void clearRepository() {
    repository.teardownForTests();
    Assertions.assertTrue(repository.getBooks().isEmpty());
  }

  @Test
  void saveBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book = repository.save(book);
    Assertions.assertNotNull(book.getId());
    Assertions.assertEquals(book.getId(), 1L);

    Book foundBook = repository.getBookById(book.getId());
    Assertions.assertNotNull(foundBook);
    Assertions.assertEquals(foundBook.getName(), book.getName());
    Assertions.assertEquals(foundBook.getAuthorName(), book.getAuthorName());
  }

  @Test
  void saveMultipleBook_shouldBeSuccessful() {
    Book firstBook = new Book(BOOK_NAME, AUTHOR_NAME);
    firstBook = repository.save(firstBook);
    Assertions.assertNotNull(firstBook.getId());

    Book secondBook = new Book(BOOK_NAME, AUTHOR_NAME);
    secondBook = repository.save(secondBook);
    Assertions.assertNotNull(secondBook.getId());

    Assertions.assertNotEquals(firstBook.getId(), secondBook.getId());
    Assertions.assertEquals(repository.getBooks().size(), 2);
  }

  @Test
  void updateBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book = repository.save(book);
    Assertions.assertNotNull(book.getId());

    Book foundBook = repository.getBookById(book.getId());
    Assertions.assertNotNull(foundBook);
    Assertions.assertEquals(foundBook.getName(), book.getName());
    Assertions.assertEquals(foundBook.getAuthorName(), book.getAuthorName());

    foundBook.setName(NEW_BOOK_NAME);
    foundBook.setAuthorName(NEW_AUTHOR_NAME);
    book = repository.save(book);

    Assertions.assertNotNull(book);
    Assertions.assertEquals(book.getId(), foundBook.getId());
    Assertions.assertEquals(book.getName(), NEW_BOOK_NAME);
    Assertions.assertEquals(book.getAuthorName(), NEW_AUTHOR_NAME);
    Assertions.assertEquals(repository.getBooks().size(), 1);
  }

  @Test
  void deleteBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book = repository.save(book);
    Assertions.assertNotNull(book.getId());

    repository.delete(book);
    Assertions.assertTrue(repository.getBooks().isEmpty());
  }

  @Test
  void deleteNonExistingBook_shouldBeSuccessful() {
    Book book = new Book(BOOK_NAME, AUTHOR_NAME);
    book.setId(10L);
    repository.delete(book);
    Assertions.assertTrue(repository.getBooks().isEmpty());
  }
}
