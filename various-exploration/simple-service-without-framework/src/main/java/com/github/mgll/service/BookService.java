package com.github.mgll.service;

import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;

public interface BookService {
  BookResponse createBook(BookCreate dto);

  void clearForTests();
}
