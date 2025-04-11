package com.brainstation23.library_management.controller;

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
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}/borrowedBooks")
    public ResponseEntity<?> getBorrowedBooks(@PathVariable String id) {
        List<Book> books = memberService.getBorrowedBooks(id);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{memberId}/borrow/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable String memberId, @PathVariable String bookId) {
        Book book = memberService.borrowBook(memberId, bookId);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{memberId}/return/{bookId}")
    public ResponseEntity<?> returnBook(@PathVariable String memberId, @PathVariable String bookId) {
        Book book = memberService.returnBook(memberId, bookId);
        return ResponseEntity.ok(book);
    }
}

