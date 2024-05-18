package com.github.mgll.service;

import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import com.github.mgll.model.Book;
import com.github.mgll.repository.BookRepository;
import com.github.mgll.repository.InMemoryBookRepository;

// TODO: ApplicationContext which provide repository dependency
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  public BookServiceImpl() {
    this.repository = new InMemoryBookRepository();
  }

  @Override
  public BookResponse createBook(BookCreate dto) {
    Book book = Book.fromBookCreate(dto);
    book = repository.save(book);
    return new BookResponse(book);
  }

  @Override
  public void clearForTests() {
    repository.teardownForTests();
  }
}
