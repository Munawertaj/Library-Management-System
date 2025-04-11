package com.brainstation23.library_management.service;

import com.brainstation23.library_management.exception.BookNotBorrowedException;
import com.brainstation23.library_management.exception.MemberNotFoundException;
import com.brainstation23.library_management.model.Book;
import com.brainstation23.library_management.model.Member;
import com.brainstation23.library_management.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private MemberRepository memberRepo = MemberRepository.getInstance();
    private BookService bookService = new BookService();

    public Member addMember(Member member) {
        memberRepo.addMember(member);
        return member;
    }

    public Member getMemberById(String memberId) {
        Member member = memberRepo.getMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with id " + memberId + " not found");
        }
        return member;
    }

    public Book borrowBook(String memberId, String bookId) {
        Member member = getMemberById(memberId);
        Book book = bookService.borrowBook(bookId);
        member.borrowBook(bookId);
        return book;
    }

    public Book returnBook(String memberId, String bookId) {
        Member member = getMemberById(memberId);
        if (!member.getBorrowedBookIds().contains(bookId)) {
            throw new BookNotBorrowedException("Member has not borrowed the book with id " + bookId);
        }
        Book book = bookService.returnBook(bookId);
        member.returnBook(bookId);
        return book;
    }

    public List<Book> getBorrowedBooks(String memberId) {
        Member member = getMemberById(memberId);
        List<Book> borrowedBooks = new ArrayList<>();
        for (String bookId : member.getBorrowedBookIds()) {
            borrowedBooks.add(bookService.getBookById(bookId));
        }
        return borrowedBooks;
    }
}

