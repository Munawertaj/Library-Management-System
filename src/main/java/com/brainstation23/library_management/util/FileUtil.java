package com.brainstation23.library_management.util;

import com.brainstation23.library_management.model.Book;
import com.brainstation23.library_management.model.Member;

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
            while ((line = br.readLine()) != null) {
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

    public static List<Member> readMembersFromFile(String filename) {
        List<Member> members = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) return members;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Expected format: id,name,totalBorrowedBooks,[borrowedBookIds]
                String[] tokens = line.split(",", 4);
                if (tokens.length >= 3) {
                    String id = tokens[0].trim();
                    String name = tokens[1].trim();
                    int totalBorrowedBooks;

                    try {
                        totalBorrowedBooks = Integer.parseInt(tokens[2].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format for borrowed books: " + tokens[2]);
                        continue; // Skip this entry
                    }

                    Member member = new Member(id, name);

                    if (tokens.length == 4 && !tokens[3].trim().isEmpty()) {
                        String bookListStr = tokens[3].trim();
                        if (bookListStr.startsWith("[") && bookListStr.endsWith("]")) {
                            bookListStr = bookListStr.substring(1, bookListStr.length() - 1); // remove brackets
                            String[] bookIds = bookListStr.split(",");
                            for (String b : bookIds) {
                                member.borrowBook(b.trim());
                            }
                        }
                    }

                    members.add(member);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading members file: " + e.getMessage());
        }

        return members;
    }

    public static void writeMembersToFile(String filename, Collection<Member> members) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Member member : members) {
                StringBuilder sb = new StringBuilder();
                sb.append(member.getId()).append(",")
                        .append(member.getName()).append(",")
                        .append(member.totalBorrowedBooks()).append(",");

                List<String> borrowedBookIds = member.getBorrowedBookIds();
                if (borrowedBookIds != null && !borrowedBookIds.isEmpty()) {
                    sb.append("[")
                            .append(String.join(",", borrowedBookIds))
                            .append("]");
                } else {
                    sb.append("[]");
                }

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing members file: " + e.getMessage());
        }
    }

}
