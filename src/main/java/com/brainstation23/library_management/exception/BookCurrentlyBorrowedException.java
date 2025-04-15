package com.brainstation23.library_management.exception;

public class BookCurrentlyBorrowedException extends RuntimeException {
    public BookCurrentlyBorrowedException(String message) {
        super(message);
    }
}
