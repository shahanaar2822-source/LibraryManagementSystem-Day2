# Library Management System — UML Class Diagram

Open this file on GitHub or in a Markdown preview that supports Mermaid diagrams.

```mermaid
classDiagram
    class Book {
      -String isbn
      -String title
      -String author
      -boolean available
      +getIsbn() String
      +isAvailable() boolean
      +equals(Object) boolean
      +hashCode() int
    }
    class Member {
      <<abstract>>
      -String id
      -String name
      +getBorrowLimit() int*
    }
    class StudentMember {
      +getBorrowLimit() int
    }
    class TeacherMember {
      +getBorrowLimit() int
    }
    class Loan {
      -Book book
      -Member member
      -LocalDate borrowedOn
      -LocalDate dueOn
      -LocalDate returnedOn
      +isActive() boolean
    }
    class BorrowingService {
      <<interface>>
      +borrowBook(memberId, isbn) Loan
      +returnBook(isbn) void
      +getActiveLoans() List~Loan~
    }
    class LibraryService {
      -BookRepository books
      -MemberRepository members
      -List~Loan~ loans
    }
    class BookRepository {
      <<interface>>
      +add(book) void
      +findByIsbn(isbn) Book
    }
    class MemberRepository {
      <<interface>>
      +add(member) void
      +findById(id) Member
    }
    class InMemoryBookRepository
    class InMemoryMemberRepository

    Member <|-- StudentMember
    Member <|-- TeacherMember
    BorrowingService <|.. LibraryService
    BookRepository <|.. InMemoryBookRepository
    MemberRepository <|.. InMemoryMemberRepository
    LibraryService --> BookRepository
    LibraryService --> MemberRepository
    LibraryService o-- Loan
    Loan --> Book
    Loan --> Member
```

## Legend
- Solid triangle (`<|--`) = inheritance (`extends`)
- Dashed triangle (`<|..`) = interface implementation (`implements`)
- Arrow = uses/depends on
- Asterisk after `getBorrowLimit()` indicates an abstract method
