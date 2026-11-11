package com.example.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookRepository books = new InMemoryBookRepository();
        MemberRepository members = new InMemoryMemberRepository();
        BorrowingService library = new LibraryService(books, members);

        // Sample data so you can test immediately.
        books.add(new Book("B101", "Java Basics", "Alex"));
        books.add(new Book("B102", "Clean Code", "Robert C. Martin"));
        books.add(new Book("B103", "Data Structures", "Sam"));
        members.add(new StudentMember("S101", "Anu"));
        members.add(new TeacherMember("T101", "Ravi"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. View all books");
            System.out.println("2. Add a book");
            System.out.println("3. Register a member");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. View active loans");
            System.out.println("7. View members");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> books.findAll().forEach(System.out::println);
                    case "2" -> {
                        System.out.print("Book ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        books.add(new Book(isbn, title, scanner.nextLine()));
                        System.out.println("Book added!");
                    }
                    case "3" -> {
                        System.out.print("Member ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Member name: ");
                        String name = scanner.nextLine();
                        System.out.print("Student or Teacher? (S/T): ");
                        String type = scanner.nextLine().trim();
                        if (type.equalsIgnoreCase("S")) members.add(new StudentMember(id, name));
                        else if (type.equalsIgnoreCase("T")) members.add(new TeacherMember(id, name));
                        else throw new IllegalArgumentException("Type must be S or T.");
                        System.out.println("Member registered!");
                    }
                    case "4" -> {
                        System.out.print("Member ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Book ISBN: ");
                        Loan loan = library.borrowBook(id, scanner.nextLine());
                        System.out.println("Borrowed! " + loan);
                    }
                    case "5" -> {
                        System.out.print("Book ISBN: ");
                        library.returnBook(scanner.nextLine());
                        System.out.println("Book returned!");
                    }
                    case "6" -> {
                        if (library.getActiveLoans().isEmpty()) System.out.println("No active loans.");
                        else library.getActiveLoans().forEach(System.out::println);
                    }
                    case "7" -> members.findAll().forEach(System.out::println);
                    case "0" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Choose a number from the menu.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
