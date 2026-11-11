# Library Management System — Java 21 / Maven

A beginner-friendly console project for the **OOP Deep Dive** internship task.

## Run in IntelliJ IDEA
1. Unzip the project.
2. Open **IntelliJ IDEA → File → Open**, select the `LibraryManagementSystem` folder (the one containing `pom.xml`).
3. Let IntelliJ import the Maven project, and set **Project SDK = Java 21**.
4. Open `src/main/java/com/example/library/Main.java`.
5. Click the green **Run ▶** icon next to `main`.
6. Use the menu in the **Run** console; enter each requested value and press Enter.

## Test immediately
The application starts with:
- Books: **B101**, **B102**, **B103**
- Members: **S101** (student, max 2 books), **T101** (teacher, max 5 books)

Try:
1. Choose `1` to view books.
2. Choose `4`, enter `S101`, then `B101` to borrow a book.
3. Choose `1` again: B101 is now **Borrowed**.
4. Choose `5`, enter `B101` to return it.
5. Choose `6` to see active loans.

## Test rules
Run `src/test/java/com/example/library/LibrarySelfTest.java` as a Java application.
Expected output: `All library tests passed!`

## OOP concepts mapped to code
| Topic | Where to see it |
|---|---|
| Classes and objects | `Book.java`, `Main.java` |
| Encapsulation | Private fields and getters in `Book.java` |
| Inheritance | `StudentMember` and `TeacherMember` extend `Member` |
| Polymorphism | Different `getBorrowLimit()` implementations |
| Abstract class | `abstract sealed class Member` |
| Interfaces | `BorrowingService`, `BookRepository`, `MemberRepository` |
| Sealed classes | `Member permits StudentMember, TeacherMember` |
| equals and hashCode | `Book` compares books using ISBN |

## SOLID (basic examples)
- **S — Single Responsibility:** `Book` stores book details; `LibraryService` handles loans; repositories store/locate books and members.
- **O — Open/Closed:** You can add another `BookRepository` implementation without changing `LibraryService`.
- **L — Liskov Substitution:** `StudentMember` and `TeacherMember` work wherever a `Member` is expected.
- **I — Interface Segregation:** Borrowing operations and storage operations use separate interfaces.
- **D — Dependency Inversion:** `LibraryService` accepts `BookRepository` and `MemberRepository` interfaces in its constructor.

## UML diagram
See **[Library_UML.png](Library_UML.png)** for the ready-to-view class diagram and **[UML.md](UML.md)** for an editable Mermaid version (GitHub displays it).

## Notes
- This is a **console application**, not a web page.
- Information is saved **in memory only**, so changes disappear when you stop the program.
- Borrowing period is **14 days**. Student limit = 2; teacher limit = 5.
- Java 21 is used for sealed classes and modern `switch` syntax.
- No external Maven dependencies are required.
