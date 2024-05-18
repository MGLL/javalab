package com.github.mgll.service;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;

import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// TODO: getBook, getBooks, deleteBook
// TODO: Change to ApplicationContext and Mock it with repository
public class BookServiceTest {

  private final BookService service = new BookServiceImpl();

  @AfterEach
  public void clearRepository() {
    service.clearForTests();
  }

  @Test
  void createBook_shouldBeSuccessful() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);
    BookResponse response = service.createBook(dto);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getId());
    Assertions.assertEquals(response.getId(), 1L);
    Assertions.assertEquals(response.getName(), BOOK_NAME);
    Assertions.assertEquals(response.getAuthorName(), AUTHOR_NAME);
  }
}
