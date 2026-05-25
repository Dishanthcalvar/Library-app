package com.demo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Member member1;

    @Before
    public void setUp() {
        library = new Library();
        book1 = new Book("ISBN001", "Clean Code", "Robert Martin");
        member1 = new Member("M001", "Alice");
        library.addBook(book1);
        library.registerMember(member1);
    }

    @Test
    public void testAddBook() {
        assertEquals(1, library.getTotalBooks());
    }

    @Test
    public void testRegisterMember() {
        assertEquals(1, library.getTotalMembers());
    }

    @Test
    public void testBorrowBook() {
        String result = library.borrowBook("M001", "ISBN001");
        assertTrue(result.contains("Success"));
        assertFalse(book1.isAvailable());
    }

    @Test
    public void testBorrowAlreadyCheckedOutBook() {
        library.borrowBook("M001", "ISBN001");
        Member member2 = new Member("M002", "Bob");
        library.registerMember(member2);
        String result = library.borrowBook("M002", "ISBN001");
        assertTrue(result.contains("checked out"));
    }

    @Test
    public void testReturnBook() {
        library.borrowBook("M001", "ISBN001");
        String result = library.returnBook("M001", "ISBN001");
        assertTrue(result.contains("Success"));
        assertTrue(book1.isAvailable());
    }

    @Test
    public void testBorrowLimitEnforced() {
        library.addBook(new Book("ISBN002", "Book Two", "Author A"));
        library.addBook(new Book("ISBN003", "Book Three", "Author B"));
        library.addBook(new Book("ISBN004", "Book Four", "Author C"));
        library.borrowBook("M001", "ISBN001");
        library.borrowBook("M001", "ISBN002");
        library.borrowBook("M001", "ISBN003");
        String result = library.borrowBook("M001", "ISBN004");
        assertTrue(result.contains("borrow limit"));
    }

    @Test
    public void testSearchByAuthor() {
        assertEquals(1, library.searchByAuthor("Robert Martin").size());
        assertEquals(0, library.searchByAuthor("Unknown Author").size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateBookIsbn() {
        library.addBook(new Book("ISBN001", "Duplicate", "Someone"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBookIsbn() {
        new Book("", "Bad Book", "Author");
    }
}