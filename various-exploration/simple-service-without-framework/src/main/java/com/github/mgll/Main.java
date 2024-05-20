package com.github.mgll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mgll.dto.BookCreate;
import com.github.mgll.dto.BookResponse;
import com.github.mgll.service.BookService;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class Main {

  public static final String CONTENT_TYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application/json";

  private static BookService bookService;
  private static final ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) throws IOException {
    int serverPort = 8000;
    ApplicationBookContext context = ApplicationBookContext.getInstance();
    bookService = context.service();

    HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
    registerTestEndpoint(server);
    registerBookEndpoints(server);

    server.setExecutor(null);
    server.start();

    System.out.println("Server started on http://localhost:8000");
  }

  // TODO: BookController / BookRequestHandler ?
  private static void registerBookEndpoints(HttpServer server) {
    server.createContext("/api/books", (exchange -> {
      if ("GET".equals(exchange.getRequestMethod())) {
        List<BookResponse> bookResponses = bookService.getBooks();
        byte[] response = mapper.writeValueAsBytes(bookResponses);
        exchange.getResponseHeaders().put(CONTENT_TYPE, List.of(APPLICATION_JSON));
        exchange.sendResponseHeaders(200, response.length);
        OutputStream output = exchange.getResponseBody();
        output.write(response);
        output.flush();
      } else {
        exchange.sendResponseHeaders(405, -1);
      }
      exchange.close();
    }));

    System.out.println("Created GET /api/books...");
  }

  private static void registerTestEndpoint(HttpServer server) {
    server.createContext("/api/books/test", (exchange -> {
      if ("POST".equals(exchange.getRequestMethod())) {
        BookCreate create = new BookCreate("Test Book", "Test Author");
        BookResponse bookResponse = bookService.createBook(create);
        byte[] response = mapper.writeValueAsBytes(bookResponse);
        exchange.getResponseHeaders().put(CONTENT_TYPE, List.of(APPLICATION_JSON));
        exchange.sendResponseHeaders(201, response.length);
        OutputStream output = exchange.getResponseBody();
        output.write(response);
        output.flush();
      } else {
        exchange.sendResponseHeaders(405, -1);
      }
      exchange.close();
    }));

    System.out.println("Created POST /api/books/test...");
  }
}
