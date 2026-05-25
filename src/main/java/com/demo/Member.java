package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private List<String> borrowedIsbns;
    private static final int MAX_BORROW_LIMIT = 3;

    public Member(String memberId, String name) {
        if (memberId == null || memberId.isEmpty())
            throw new IllegalArgumentException("Member ID cannot be empty");
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.memberId = memberId;
        this.name = name;
        this.borrowedIsbns = new ArrayList<>();
    }

    public String getMemberId()            { return memberId; }
    public String getName()                { return name; }
    public List<String> getBorrowedIsbns() { return borrowedIsbns; }

    public boolean canBorrow() {
        return borrowedIsbns.size() < MAX_BORROW_LIMIT;
    }

    public void borrowBook(String isbn) { borrowedIsbns.add(isbn); }
    public void returnBook(String isbn) { borrowedIsbns.remove(isbn); }

    @Override
    public String toString() {
        return String.format("Member[%s]: %s | Books borrowed: %d/%d",
                memberId, name, borrowedIsbns.size(), MAX_BORROW_LIMIT);
    }
}