package com.example.library;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class InMemoryMemberRepository implements MemberRepository {
    private final Map<String, Member> members = new LinkedHashMap<>();

    @Override
    public void add(Member member) {
        if (members.containsKey(member.getId())) {
            throw new IllegalArgumentException("Member already exists: " + member.getId());
        }
        members.put(member.getId(), member);
    }

    @Override
    public Member findById(String memberId) { return members.get(memberId.trim()); }

    @Override
    public List<Member> findAll() { return new ArrayList<>(members.values()); }
}
