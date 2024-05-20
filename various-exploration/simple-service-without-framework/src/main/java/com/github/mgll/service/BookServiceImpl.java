package com.github.mgll.service;

import com.github.mgll.ApplicationBookContextSupport;
import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import com.github.mgll.dto.BookUpdate;
import com.github.mgll.exception.BookNotFoundException;
import com.github.mgll.model.Book;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService, ApplicationBookContextSupport {

  @Override
  public BookResponse createBook(BookCreate dto) {
    Book book = Book.fromBookCreate(dto);
    book = repository().save(book);
    return new BookResponse(book);
  }

  @Override
  public BookResponse updateBook(BookUpdate dto) throws BookNotFoundException {
    Optional<Book> optionalBook = repository().getBookById(dto.getId());
    if (optionalBook.isEmpty()) throw new BookNotFoundException();
    Book updatedBook = Book.fromBookUpdate(dto);
    updatedBook = repository().save(updatedBook);
    return new BookResponse(updatedBook);
  }

  @Override
  public BookResponse getBook(Long id) throws BookNotFoundException {
    Optional<Book> optionalBook = repository().getBookById(id);
    if (optionalBook.isEmpty()) throw new BookNotFoundException();
    return new BookResponse(optionalBook.get());
  }

  @Override
  public List<BookResponse> getBooks() {
    return BookResponse.fromBooks(repository().getBooks());
  }

  @Override
  public void deleteBook(Long id) throws BookNotFoundException {
    Optional<Book> optionalBook = repository().getBookById(id);
    if (optionalBook.isEmpty()) throw new BookNotFoundException();
    repository().delete(optionalBook.get());
  }

  @Override
  public void clearForTests() {
    repository().teardownForTests();
  }
}
