package com.github.mgll.service;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.BOOK_NOT_FOUND_EXCEPTION_MSG;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;

import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import com.github.mgll.dto.BookUpdate;
import com.github.mgll.exception.BookNotFoundException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// TODO: Change to ApplicationContext and Mock it with repository
public class BookServiceTest {

  private final BookService service = new BookServiceImpl();

  @AfterEach
  public void clearRepository() {
    service.clearForTests();
  }

  @Test
  void createBook_shouldCreateBook() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    BookResponse response = service.createBook(dto);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getId());
    Assertions.assertEquals(1L, response.getId());
    Assertions.assertEquals(BOOK_NAME, response.getName());
    Assertions.assertEquals(AUTHOR_NAME, response.getAuthorName());
  }

  @Test
  void updateBook_shouldModifyBook() {
    BookCreate createDto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    Long id = service.createBook(createDto).getId();

    BookUpdate updateDto = new BookUpdate(id, NEW_BOOK_NAME, NEW_AUTHOR_NAME);
    service.updateBook(updateDto);

    List<BookResponse> bookResponses = service.getBooks();
    Assertions.assertEquals(1, bookResponses.size());

    BookResponse bookResponse = bookResponses.getFirst();
    Assertions.assertEquals(1L, bookResponse.getId());
    Assertions.assertEquals(NEW_BOOK_NAME, bookResponse.getName());
    Assertions.assertEquals(NEW_AUTHOR_NAME, bookResponse.getAuthorName());
  }

  @Test
  void updateBook_shouldThrowBookNotFoundException() {
    Exception exception = Assertions.assertThrows(BookNotFoundException.class, () -> {
      BookUpdate updateDto = new BookUpdate(1L, NEW_BOOK_NAME, NEW_AUTHOR_NAME);
      service.updateBook(updateDto);
    });
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }

  @Test
  void getExistingBook_shouldReturnBookResponse() {
    service.createBook(new BookCreate(BOOK_NAME, AUTHOR_NAME));
    BookResponse response = service.getBook(1L);
    Assertions.assertNotNull(response);
  }

  @Test
  void getNonExistingBook_shouldThrowBookNotFoundException() {
    Exception exception = Assertions.assertThrows(BookNotFoundException.class, () -> {
      service.getBook(1L);
    });
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }

  @Test
  void getBooks_shouldBeEmpty() {
    List<BookResponse> bookResponses = service.getBooks();
    Assertions.assertTrue(bookResponses.isEmpty());
  }

  @Test
  void getBooks_shouldNotBeEmpty() {
    service.createBook(new BookCreate(BOOK_NAME, AUTHOR_NAME));
    List<BookResponse> bookResponses = service.getBooks();
    Assertions.assertFalse(bookResponses.isEmpty());
  }

  @Test
  void deleteBook_shouldRemoveBook() {
    service.createBook(new BookCreate(BOOK_NAME, AUTHOR_NAME));
    List<BookResponse> bookResponses = service.getBooks();
    Assertions.assertEquals(1, bookResponses.size());

    service.deleteBook(bookResponses.getFirst().getId());
    bookResponses = service.getBooks();
    Assertions.assertTrue(bookResponses.isEmpty());
  }

  @Test
  void deleteBook_shouldThrowBookNotFoundException() {
    Exception exception = Assertions.assertThrows(BookNotFoundException.class, () -> {
      service.deleteBook(1L);
    });
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }
}
