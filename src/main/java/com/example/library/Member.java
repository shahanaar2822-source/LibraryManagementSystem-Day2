package com.example.library;

// Abstract: cannot create new Member() directly.
// Sealed: only these two types may directly extend Member.
public abstract sealed class Member permits StudentMember, TeacherMember {
    private final String id;
    private final String name;

    protected Member(String id, String name) {
        if (id == null || id.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Member ID and name are required.");
        }
        this.id = id.trim();
        this.name = name.trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }

    // Polymorphism: each child decides its own borrowing limit.
    public abstract int getBorrowLimit();

    @Override
    public String toString() {
        return id + " | " + name + " | " + getClass().getSimpleName() +
                " | Max books: " + getBorrowLimit();
    }
}
