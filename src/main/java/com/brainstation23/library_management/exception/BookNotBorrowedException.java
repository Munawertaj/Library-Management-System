package com.brainstation23.library_management.exception;

public class BookNotBorrowedException extends RuntimeException {
    public BookNotBorrowedException(String message) {
        super(message);
    }
}
