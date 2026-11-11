package com.example.library;

public final class TeacherMember extends Member {
    public TeacherMember(String id, String name) { super(id, name); }

    @Override
    public int getBorrowLimit() { return 5; }
}
