package com.s36414.library;

public class Main {
    static void main(String[] args) {
        Book book = new Book(
                "0140449264",
                "The Count of Monte Cristo",
                "Alexandre Dumas",
                2003
        );

        System.out.println(book);

        User user = new User("1", "Jan Kowalski", "jan@kowalski.pl");
        System.out.println(user);
    }
}
