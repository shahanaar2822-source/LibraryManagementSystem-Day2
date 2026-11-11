package com.example.library;

import java.util.List;

public interface MemberRepository {
    void add(Member member);
    Member findById(String memberId);
    List<Member> findAll();
}
