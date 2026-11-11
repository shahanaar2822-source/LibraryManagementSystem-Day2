package com.example.library;

import java.util.ArrayList;
import java.util.List;

// Depends on repository interfaces, not a particular storage technology.
public final class LibraryService implements BorrowingService {
    private final BookRepository books;
    private final MemberRepository members;
    private final List<Loan> loans = new ArrayList<>();

    public LibraryService(BookRepository books, MemberRepository members) {
        this.books = books;
        this.members = members;
    }

    @Override
    public Loan borrowBook(String memberId, String isbn) {
        Member member = members.findById(memberId);
        Book book = books.findByIsbn(isbn);
        if (member == null) throw new IllegalArgumentException("Member not found: " + memberId);
        if (book == null) throw new IllegalArgumentException("Book not found: " + isbn);
        if (!book.isAvailable()) throw new IllegalStateException("This book is already borrowed.");

        long count = loans.stream().filter(Loan::isActive)
                .filter(loan -> loan.getMember().getId().equals(memberId.trim()))
                .count();
        if (count >= member.getBorrowLimit()) {
            throw new IllegalStateException("Borrowing limit reached for " + member.getName());
        }

        book.markBorrowed();
        Loan loan = new Loan(book, member);
        loans.add(loan);
        return loan;
    }

    @Override
    public void returnBook(String isbn) {
        Loan loan = loans.stream()
                .filter(Loan::isActive)
                .filter(item -> item.getBook().getIsbn().equals(isbn.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No active loan for book: " + isbn));
        loan.markReturned();
        loan.getBook().markReturned();
    }

    @Override
    public List<Loan> getActiveLoans() {
        return loans.stream().filter(Loan::isActive).toList();
    }
}
