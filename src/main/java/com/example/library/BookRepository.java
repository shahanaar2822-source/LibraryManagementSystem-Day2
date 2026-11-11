package com.example.library;

import java.util.List;

// Interface = contract: how books are stored can change later.
public interface BookRepository {
    void add(Book book);
    Book findByIsbn(String isbn);
    List<Book> findAll();
}
