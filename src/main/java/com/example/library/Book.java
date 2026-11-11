package com.example.library;

import java.util.Objects;

// Encapsulation: fields are private and accessed through getters.
public final class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean available = true;

    public Book(String isbn, String title, String author) {
        if (isbn == null || isbn.isBlank() || title == null || title.isBlank()
                || author == null || author.isBlank()) {
            throw new IllegalArgumentException("ISBN, title and author are required.");
        }
        this.isbn = isbn.trim();
        this.title = title.trim();
        this.author = author.trim();
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }

    // Package-private: borrowing is controlled by LibraryService.
    void markBorrowed() { available = false; }
    void markReturned() { available = true; }

    // Two books with the same ISBN represent the same book in this example.
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Book book)) return false;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() { return Objects.hash(isbn); }

    @Override
    public String toString() {
        return isbn + " | " + title + " by " + author +
                " | " + (available ? "Available" : "Borrowed");
    }
}
