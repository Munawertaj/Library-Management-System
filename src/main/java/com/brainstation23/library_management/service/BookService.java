package com.brainstation23.library_management.service;

import com.brainstation23.library_management.model.Book;
import com.brainstation23.library_management.repository.BookRepository;

import java.util.Collection;

public class BookService {
    private BookRepository bookRepo = BookRepository.getInstance();

    public Collection<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public Book getBookById(String id) {
        Book book = bookRepo.getBookById(id);
        if (book == null) {
            System.out.println("Book with id " + id + " not found");
        }
        return book;
    }

    public Book addBook(Book book) {
        bookRepo.addBook(book);
        return book;
    }

    public Book borrowBook(String id) {
        Book book = getBookById(id);
        if (book.getAvailableCopies() <= 0) {
            System.out.println("No copies available for book: " + book.getTitle());
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.updateBook(book);
        return book;
    }

    public Book returnBook(String id) {
        Book book = getBookById(id);
        if (book.getAvailableCopies() < book.getTotalCopies()) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookRepo.updateBook(book);
        }
        return book;
    }
}

