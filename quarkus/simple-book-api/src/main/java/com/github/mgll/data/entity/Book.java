package com.github.mgll.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Books.findAll", query = "SELECT b FROM Book b ORDER BY b.name")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public String id;
  public String name;
}
