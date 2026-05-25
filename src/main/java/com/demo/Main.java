package com.demo;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        library.addBook(new Book("ISBN001", "Clean Code", "Robert Martin"));
        library.addBook(new Book("ISBN002", "The Pragmatic Programmer", "Andrew Hunt"));
        library.addBook(new Book("ISBN003", "Design Patterns", "Gang of Four"));

        // Register members
        library.registerMember(new Member("M001", "Alice"));
        library.registerMember(new Member("M002", "Bob"));

        // Borrow and return
        System.out.println(library.borrowBook("M001", "ISBN001"));
        System.out.println(library.borrowBook("M001", "ISBN001")); // already checked out
        System.out.println(library.returnBook("M001", "ISBN001"));
        System.out.println(library.borrowBook("M002", "ISBN001")); // now available again

        // Available books
        System.out.println("\n--- Available Books ---");
        library.getAvailableBooks().forEach(System.out::println);

        // Search by author
        System.out.println("\n--- Search by Author: Robert Martin ---");
        library.searchByAuthor("Robert Martin").forEach(System.out::println);
    }
}