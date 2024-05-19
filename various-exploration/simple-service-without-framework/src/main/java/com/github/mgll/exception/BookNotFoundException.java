package com.github.mgll.exception;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException() {
    super("Book does not exist.");
  }
}
