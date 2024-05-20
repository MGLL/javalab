package com.github.mgll.service;

import static com.github.mgll.Constants.AUTHOR_NAME;
import static com.github.mgll.Constants.BOOK_NAME;
import static com.github.mgll.Constants.BOOK_NOT_FOUND_EXCEPTION_MSG;
import static com.github.mgll.Constants.NEW_AUTHOR_NAME;
import static com.github.mgll.Constants.NEW_BOOK_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.mgll.ApplicationBookContext;
import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import com.github.mgll.dto.BookUpdate;
import com.github.mgll.exception.BookNotFoundException;
import com.github.mgll.model.Book;
import com.github.mgll.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockedBookServiceTest {

  @Mock
  private BookRepository repositoryMock;

  @Mock
  private ApplicationBookContext contextMock;

  @Spy
  private BookServiceImpl service;


  @BeforeEach
  public void setUp() {
    when(contextMock.repository()).thenReturn(repositoryMock);
    doReturn(contextMock).when(service).context();
  }

  @Test
  void createBook_shouldBeSuccessful() {
    BookCreate dto = new BookCreate(BOOK_NAME, AUTHOR_NAME);

    when(repositoryMock.save(any(Book.class))).thenReturn(mockBook());
    BookResponse response = service.createBook(dto);

    verify(repositoryMock).save(any(Book.class));
    Assertions.assertNotNull(response);
    Assertions.assertEquals(BOOK_NAME, response.getName());
    Assertions.assertEquals(AUTHOR_NAME, response.getAuthorName());
  }

  @Test
  void updateBook_shouldBeSuccessFul() {
    when(repositoryMock.getBookById(anyLong())).thenReturn(Optional.of(mockBook()));

    BookUpdate updateDto = new BookUpdate(1L, NEW_BOOK_NAME, NEW_AUTHOR_NAME);
    Book updatedMockBook = Book.fromBookUpdate(updateDto);
    when(repositoryMock.save(any(Book.class))).thenReturn(updatedMockBook);

    BookResponse response = service.updateBook(updateDto);
    Assertions.assertEquals(1L, response.getId());
    Assertions.assertEquals(NEW_BOOK_NAME, response.getName());
    Assertions.assertEquals(NEW_AUTHOR_NAME, response.getAuthorName());
  }

  @Test
  void updateBook_shouldThrow() {
    when(repositoryMock.getBookById(anyLong())).thenReturn(Optional.empty());
    BookUpdate updateDto = new BookUpdate(1L, NEW_BOOK_NAME, NEW_AUTHOR_NAME);
    Exception exception = Assertions.assertThrows(
        BookNotFoundException.class, () -> service.updateBook(updateDto));
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }

  @Test
  void getBook_shouldReturnBookResponse() {
    when(repositoryMock.getBookById(anyLong())).thenReturn(Optional.of(mockBook()));
    BookResponse response = service.getBook(1L);
    Assertions.assertNotNull(response);
  }

  @Test
  void getBook_shouldThrowBookNotFoundException() {
    when(repositoryMock.getBookById(anyLong())).thenReturn(Optional.empty());
    Exception exception = Assertions.assertThrows(
        BookNotFoundException.class, () -> service.getBook(1L));
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }

  @Test
  void getBooks_shouldBeEmpty() {
    when(repositoryMock.getBooks()).thenReturn(new ArrayList<>());
    List<BookResponse> bookResponses = service.getBooks();
    Assertions.assertTrue(bookResponses.isEmpty());
  }

  @Test
  void getBooks_shouldNotBeEmpty() {
    when(repositoryMock.getBooks()).thenReturn(List.of(mockBook()));
    List<BookResponse> bookResponses = service.getBooks();
    Assertions.assertFalse(bookResponses.isEmpty());
  }

  @Test
  void deleteBook_shouldRemoveBook() {
    Book mockedBook = mockBook();
    when(repositoryMock.getBookById(anyLong())).thenReturn(Optional.of(mockedBook));
    doNothing().when(repositoryMock).delete(mockedBook);
    service.deleteBook(mockedBook.getId());
    verify(repositoryMock).delete(mockedBook);
  }

  @Test
  void deleteBook_shouldThrowBookNotFoundException() {
    when(repositoryMock.getBookById(anyLong())).thenReturn(Optional.empty());
    Exception exception = Assertions.assertThrows(
        BookNotFoundException.class, () -> service.deleteBook(1L));
    Assertions.assertTrue(exception.getMessage().contains(BOOK_NOT_FOUND_EXCEPTION_MSG));
  }

  private Book mockBook() {
    Book mockBook = new Book(BOOK_NAME, AUTHOR_NAME);
    mockBook.setId(1L);
    return mockBook;
  }
}
