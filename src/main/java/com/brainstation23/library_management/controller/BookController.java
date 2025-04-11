package com.brainstation23.library_management.controller;

import com.brainstation23.library_management.model.Book;
import com.brainstation23.library_management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/books")
public class BookController {

    private BookService bookService = new BookService();

    @GetMapping("/allBooks")
    public ResponseEntity<Collection<Book>> getAllBooks() {
        Collection<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
}

