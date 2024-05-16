package com.github.mgll.data.repository;

import com.github.mgll.data.entity.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class JdbcBookRepository implements BookRepository {

  @Inject
  EntityManager em;

  @Override
  public List<Book> getBooks() {
    return em.createNamedQuery("Books.findAll", Book.class).getResultList();
  }

  @Override
  public Book getBook(String id) {
    Book book = em.find(Book.class, id);
    if(book == null)
      throw new NotFoundException("Unknown book: " + id);
    return book;
  }
}
