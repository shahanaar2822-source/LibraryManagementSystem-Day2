package com.example.library;

import java.time.LocalDate;

// One loan connects one book and one library member.
public final class Loan {
    private final Book book;
    private final Member member;
    private final LocalDate borrowedOn;
    private final LocalDate dueOn;
    private LocalDate returnedOn;

    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.borrowedOn = LocalDate.now();
        this.dueOn = borrowedOn.plusDays(14);
    }

    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getDueOn() { return dueOn; }
    public boolean isActive() { return returnedOn == null; }
    void markReturned() { returnedOn = LocalDate.now(); }

    @Override
    public String toString() {
        return book.getTitle() + " (" + book.getIsbn() + ") -> " +
                member.getName() + ", due " + dueOn;
    }
}
