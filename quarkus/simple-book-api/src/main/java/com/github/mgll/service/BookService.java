package com.github.mgll.service;

import com.github.mgll.data.repository.BookRepository;
import com.github.mgll.dto.book.BookResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BookService {

  @Inject
  BookRepository repository;

  public List<BookResponse> getBooks() {
    return BookResponse.fromBooks(repository.getBooks());
  }

  public BookResponse getBook(String id) {
    return BookResponse.fromBook(repository.getBook(id));
  }
}
