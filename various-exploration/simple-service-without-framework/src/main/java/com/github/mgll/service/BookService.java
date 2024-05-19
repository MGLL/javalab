package com.github.mgll.service;

import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import com.github.mgll.dto.BookUpdate;
import com.github.mgll.exception.BookNotFoundException;
import java.util.List;

public interface BookService {
  BookResponse createBook(BookCreate dto);
  void updateBook(BookUpdate dto) throws BookNotFoundException;
  BookResponse getBook(Long id) throws BookNotFoundException;
  List<BookResponse> getBooks();
  void deleteBook(Long id) throws BookNotFoundException;

  void clearForTests();
}
