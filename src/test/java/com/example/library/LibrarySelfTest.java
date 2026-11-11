package com.example.library;

// Run this class to test the main features without additional libraries.
public class LibrarySelfTest {
    public static void main(String[] args) {
        BookRepository books = new InMemoryBookRepository();
        MemberRepository members = new InMemoryMemberRepository();
        BorrowingService library = new LibraryService(books, members);
        books.add(new Book("B1", "Java", "Author"));
        books.add(new Book("B2", "Algorithms", "Author"));
        books.add(new Book("B3", "Database", "Author"));
        members.add(new StudentMember("S1", "Student"));
        members.add(new TeacherMember("T1", "Teacher"));

        check(new Book("B1", "Other Title", "Someone").equals(books.findByIsbn("B1")),
                "Books with same ISBN should be equal");
        check(new Book("B1", "Different", "A").hashCode() == books.findByIsbn("B1").hashCode(),
                "Equal books should have equal hash codes");
        library.borrowBook("S1", "B1");
        library.borrowBook("S1", "B2");
        check(!books.findByIsbn("B1").isAvailable(), "Borrowed book should be unavailable");
        check(library.getActiveLoans().size() == 2, "Should have two active loans");
        expectError(() -> library.borrowBook("S1", "B3"), "Student limit must be enforced");
        expectError(() -> library.borrowBook("T1", "B1"), "Same book cannot be borrowed twice");
        library.returnBook("B1");
        check(books.findByIsbn("B1").isAvailable(), "Returned book should be available");
        library.borrowBook("T1", "B1");
        check(library.getActiveLoans().size() == 2, "Reborrow should work after return");
        System.out.println("All library tests passed!");
    }

    private static void check(boolean ok, String message) {
        if (!ok) throw new AssertionError(message);
    }

    private static void expectError(Runnable task, String message) {
        try {
            task.run();
            throw new AssertionError(message);
        } catch (IllegalStateException expected) {
            // Expected because a borrowing rule was broken.
        }
    }
}
