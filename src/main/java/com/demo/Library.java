package com.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        boolean exists = books.stream()
                .anyMatch(b -> b.getIsbn().equals(book.getIsbn()));
        if (exists)
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void registerMember(Member member) {
        boolean exists = members.stream()
                .anyMatch(m -> m.getMemberId().equals(member.getMemberId()));
        if (exists)
            throw new IllegalArgumentException("Member ID " + member.getMemberId() + " already registered");
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public String borrowBook(String memberId, String isbn) {
        Member member = findMember(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        Book book = findBook(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));

        if (!book.isAvailable())
            return "Sorry, '" + book.getTitle() + "' is currently checked out.";
        if (!member.canBorrow())
            return "Member " + member.getName() + " has reached the borrow limit of 3 books.";

        book.setAvailable(false);
        member.borrowBook(isbn);
        return "Success: " + member.getName() + " borrowed '" + book.getTitle() + "'";
    }

    public String returnBook(String memberId, String isbn) {
        Member member = findMember(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));
        Book book = findBook(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));

        if (!member.getBorrowedIsbns().contains(isbn))
            return "Member " + member.getName() + " did not borrow this book.";

        book.setAvailable(true);
        member.returnBook(isbn);
        return "Success: " + member.getName() + " returned '" + book.getTitle() + "'";
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) result.add(b);
        }
        return result;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) result.add(b);
        }
        return result;
    }

    public int getTotalBooks()   { return books.size(); }
    public int getTotalMembers() { return members.size(); }

    private Optional<Book> findBook(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
    }

    private Optional<Member> findMember(String memberId) {
        return members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst();
    }
}