package com.brainstation23.library_management.util;

import com.brainstation23.library_management.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileUtil {
    public static List<Book> readBooksFromFile(String filename) {
        List<Book> books = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return books;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // Expected format: id,title,author,totalCopies,availableCopies
                String[] tokens = line.split(",");
                if(tokens.length == 5) {
                    String id = tokens[0];
                    String title = tokens[1];
                    String author = tokens[2];
                    int totalCopies = Integer.parseInt(tokens[3]);
                    int availableCopies = Integer.parseInt(tokens[4]);
                    books.add(new Book(id, title, author, totalCopies, availableCopies));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
        return books;
    }

    public static void writeBooksToFile(String filename, Collection<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : books) {
                String line = book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," +
                        book.getTotalCopies() + "," + book.getAvailableCopies();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing books file: " + e.getMessage());
        }
    }
}
