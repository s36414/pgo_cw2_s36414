package com.s36414.library;

import java.util.LinkedList;
import java.util.List;

public class Library {
    List<Book> bookList;

    public Library() {
        bookList = new LinkedList<>();
    }

    public Library(List<Book> bookList) {
        this.bookList = bookList;
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
                .ifPresent(book -> book.setAvailable(false));
    }

    public List<Book> getAvailableBooks() {
        return bookList.stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public List<Book> findBooks(String query) {
        return bookList.stream().filter(book ->
                        book.getIsbn().contains(query)
                                || book.getAuthor().contains(query)
                                || book.getTitle().contains(query))
                .toList();
    }
}
