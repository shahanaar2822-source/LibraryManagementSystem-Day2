package com.example.library;

// Inheritance: StudentMember IS A Member.
public final class StudentMember extends Member {
    public StudentMember(String id, String name) { super(id, name); }

    @Override
    public int getBorrowLimit() { return 2; }
}
