package com.github.mgll.resource;

import com.github.mgll.dto.book.BookResponse;
import com.github.mgll.service.BookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

  @Inject
  BookService service;

  @GET
  public List<BookResponse> getBooks() {
    return service.getBooks();
  }

  @GET
  @Path("/{id}")
  public BookResponse getBook(String id) {
    return service.getBook(id);
  }
}
