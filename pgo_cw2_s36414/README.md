# Library Management System

A simple Java library management system that handles books, users, and rentals.

## Structure

| Class | Description |
|-------|-------------|
| `Book` | Represents a book with ISBN, title, author, publication year, and availability status |
| `User` | Represents a library user with ID, name, and email |
| `Library` | Core class managing the book collection and rentals |
| `Main` | Entry point with demo usage |

## Features

- Add books to the library
- Search books by ISBN, title, or author
- Rent and return books
- View available books
- Generate rental reports per user

## Usage

```java
// Create books and library
List<Book> books = Main.createTestBooks();
Library library = new Library(books);

// Create a user
User user = new User("1", "Jan Kowalski", "jan@kowalski.pl");

// Search for a book
List<Book> results = library.findBooks("Tolkien");

// Rent a book
library.rentBook("0618640150", user);

// Return a book
library.returnBook("0618640150", user);

// Print rental report
library.makeUserRentReport(user);
```

## Error Handling

| Exception | Thrown when |
|-----------|-------------|
| `BookNotFoundException` | ISBN does not exist in the library |
| `BookNotAvailableException` | Book exists but is currently rented out |

## Package

`com.s36414.library`