package com.example.library;

import java.util.List;

// Interface: what a borrowing service MUST be able to do.
public interface BorrowingService {
    Loan borrowBook(String memberId, String isbn);
    void returnBook(String isbn);
    List<Loan> getActiveLoans();
}
