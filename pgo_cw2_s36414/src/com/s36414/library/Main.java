package com.s36414.library;

import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    static void main(String[] args) {

        List<Book> books = createTestBooks();
        for (Book book : books) {
            System.out.println(book);
        }

        User user = new User("1", "Jan Kowalski", "jan@kowalski.pl");
        System.out.println(user);

        Library library = new Library(books);

        System.out.printf("Library books count: %d%n", library.getBooksCount());

        String query = "Tolkien";

        List<Book> foundBooks = library.findBooks(query);
        for (Book book : foundBooks) {
            System.out.println("Found books:");
            System.out.println(book);
        }

        try {
            Book foundBook = foundBooks.getFirst();
            foundBook.setAvailable(false);
        } catch (NoSuchElementException e) {
            System.out.printf("No books found for query: %s%n", query);
        }

        System.out.println("Available books:");
        for (Book availableBooks : library.getAvailableBooks()) {
            System.out.println(availableBooks);
        }
    }

    public static List<Book> createTestBooks() {
        return List.of(
                new Book(
                        "0140449264",
                        "The Count of Monte Cristo",
                        "Alexandre Dumas",
                        2003
                ),
                new Book(
                        "0618640150",
                        "The Lord of the Rings",
                        "J.R.R. Tolkien",
                        1954
                ),
                new Book(
                        "0374528373",
                        "Crime and Punishment",
                        "Fyodor Dostoevsky",
                        1866
                )
        );
    }
}
