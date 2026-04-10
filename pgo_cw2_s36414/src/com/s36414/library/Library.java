package com.s36414.library;

import java.util.*;

public class Library {
    private List<Book> bookList;
    private Map<String, List<Book>> rentals;

    public Library() {
        bookList = new LinkedList<>();
        rentals = new HashMap<>(10);
    }

    public Library(List<Book> bookList) {
        this.bookList = bookList;
        this.rentals = new HashMap<>(10);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public int getBooksCount() {
        return bookList.size();
    }

    public void setBookAvailable(String isbn, Boolean available) {
        bookList.stream().filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .ifPresent(book -> book.setAvailable(available));
    }

    public List<Book> getAvailableBooks() {
        return bookList.stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public List<Book> findBooks(String query) {
        return bookList.stream().filter(book ->
                        book.getIsbn().equals(query)
                                || book.getAuthor().contains(query)
                                || book.getTitle().contains(query))
                .toList();
    }

    private Book findBook(String isbn) {
        return bookList.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst().orElse(null);
    }

    private Book findAvailableBook(String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        } else if (!book.isAvailable()) {
            throw new BookNotAvailableException(isbn);
        } else {
            return book;
        }
    }

    public void rentBook(String isbn, User user) {
        Book book = findAvailableBook(isbn);

        book.setAvailable(false);
        rentals.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(book);
    }

    public void returnBook(String isbn, User user) {
        Book book = findBook(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        } else {
            book.setAvailable(true);
            List<Book> userRentals = rentals.get(user.getId());
            if (userRentals != null) userRentals.remove(book);
        }
    }

    public void makeUserRentReport(User user) {
        System.out.println("User rented books:");
        List<Book> userRentedBooks = getUserRentedBooks(user);
        if (userRentedBooks == null) {
            System.out.println("User not rented any book yet!");
        } else {
            for (Book book : userRentedBooks) {
                System.out.println(book);
            }
        }
    }

    private List<Book> getUserRentedBooks(User user) {
        return rentals.get(user.getId());
    }
}

abstract class LibraryException extends RuntimeException {
    public LibraryException(String error) {
        super(error);
    }
}

class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String isbn) {
        super(String.format("Book (isbn:%s) not found!", isbn));
    }
}

class BookNotAvailableException extends LibraryException {
    public BookNotAvailableException(String isbn) {
        super(String.format("Book (isbn:%s) is not available!", isbn));
    }
}