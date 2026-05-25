package com.demo;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author) {
        if (isbn == null || isbn.isEmpty())
            throw new IllegalArgumentException("ISBN cannot be empty");
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Title cannot be empty");
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getIsbn()      { return isbn; }
    public String getTitle()     { return title; }
    public String getAuthor()    { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s — %s",
                isbn, title, author, isAvailable ? "Available" : "Checked Out");
    }
}