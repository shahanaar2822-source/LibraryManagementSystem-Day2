package com.example.library;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> books = new LinkedHashMap<>();

    @Override
    public void add(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book already exists: " + book.getIsbn());
        }
        books.put(book.getIsbn(), book);
    }

    @Override
    public Book findByIsbn(String isbn) { return books.get(isbn.trim()); }

    @Override
    public List<Book> findAll() { return new ArrayList<>(books.values()); }
}
