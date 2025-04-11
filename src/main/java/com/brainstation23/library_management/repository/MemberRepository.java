package com.brainstation23.library_management.repository;

import com.brainstation23.library_management.model.Member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepository {
    private Map<String, Member> memberStorage;
    private static MemberRepository instance;

    private MemberRepository() {
        memberStorage = new ConcurrentHashMap<>();
    }

    public static synchronized MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    public void addMember(Member member) {
        memberStorage.put(member.getId(), member);
    }

    public Member getMemberById(String memberId) {
        return memberStorage.get(memberId);
    }
}

