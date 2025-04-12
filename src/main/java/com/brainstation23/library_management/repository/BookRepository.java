package com.brainstation23.library_management.repository;

import com.brainstation23.library_management.model.Book;
import com.brainstation23.library_management.util.FileUtil;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookRepository {
    private static final String BOOK_FILE = "src/main/java/com/brainstation23/library_management/util/books.csv";
    private Map<String, Book> bookStorage;
    private static BookRepository instance;

    private BookRepository() {
        bookStorage = new LinkedHashMap<>();
        for (Book book : FileUtil.readBooksFromFile(BOOK_FILE)) {
            bookStorage.put(book.getId(), book);
        }
    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public Collection<Book> getAllBooks() {
        return bookStorage.values();
    }

    public Book getBookById(String id) {
        return bookStorage.get(id);
    }

    public void addBook(Book book) {
        bookStorage.put(book.getId(), book);
        FileUtil.writeBooksToFile(BOOK_FILE, bookStorage.values());
    }

    public void updateBook(Book book) {
        bookStorage.put(book.getId(), book);
        FileUtil.writeBooksToFile(BOOK_FILE, bookStorage.values());
    }
}

