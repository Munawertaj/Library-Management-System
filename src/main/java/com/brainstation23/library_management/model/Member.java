package com.brainstation23.library_management.model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private List<String> borrowedBookIds;

    // Default constructor (required for JSON deserialization)
    public Member() {
        this.borrowedBookIds = new ArrayList<>();
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public int totalBorrowedBooks() {
        return borrowedBookIds.size();
    }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return "Member {id='" + id + "', name='" + name + "', borrowedBooks=" + totalBorrowedBooks() + "}";
    }
}

