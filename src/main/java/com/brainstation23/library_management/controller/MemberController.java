package com.brainstation23.library_management.controller;

import com.brainstation23.library_management.exception.BookNotBorrowedException;
import com.brainstation23.library_management.exception.BookNotFoundException;
import com.brainstation23.library_management.exception.BookNotAvailableException;
import com.brainstation23.library_management.exception.MemberNotFoundException;
import com.brainstation23.library_management.model.Book;
import com.brainstation23.library_management.model.Member;
import com.brainstation23.library_management.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    private MemberService memberService = new MemberService();

    @PostMapping("/addMember")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member createdMember = memberService.addMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getMember(@PathVariable String id) {
        try {
            Member member = memberService.getMemberById(id);
            return ResponseEntity.ok(member);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/borrowedBooks")
    public ResponseEntity<?> getBorrowedBooks(@PathVariable String id) {
        try {
            List<Book> books = memberService.getBorrowedBooks(id);
            return ResponseEntity.ok(books);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{memberId}/borrow/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable String memberId, @PathVariable String bookId) {
        try {
            Book book = memberService.borrowBook(memberId, bookId);
            return ResponseEntity.ok(book);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BookNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{memberId}/return/{bookId}")
    public ResponseEntity<?> returnBook(@PathVariable String memberId, @PathVariable String bookId) {
        try {
            Book book = memberService.returnBook(memberId, bookId);
            return ResponseEntity.ok(book);
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (BookNotBorrowedException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

