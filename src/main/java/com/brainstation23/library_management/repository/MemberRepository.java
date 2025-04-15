package com.brainstation23.library_management.repository;

import com.brainstation23.library_management.model.Member;
import com.brainstation23.library_management.util.FileUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemberRepository {
    private static final String MEMBER_FILE = "src/main/java/com/brainstation23/library_management/util/members.csv";
    private Map<String, Member> memberStorage;
    private static MemberRepository instance;

    private MemberRepository() {
        memberStorage = new LinkedHashMap<>();
        for (Member member : FileUtil.readMembersFromFile(MEMBER_FILE)) {
            memberStorage.put(member.getId(), member);
        }
    }

    public static synchronized MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    public Member getMemberById(String memberId) {
        return memberStorage.get(memberId);
    }

    public void addMember(Member member) {
        memberStorage.put(member.getId(), member);
        FileUtil.writeMembersToFile(MEMBER_FILE, memberStorage.values());
    }

    public void updateMember(Member member) {
        memberStorage.put(member.getId(), member);
        FileUtil.writeMembersToFile(MEMBER_FILE, memberStorage.values());
    }
}

