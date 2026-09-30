package com.library.model;

public class Book {
    private int id, quantity, availableQuantity;
    private String title, author, isbn, category;

    public Book() {}

    public Book(String title, String author, String isbn, String category, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int v) { availableQuantity = v; }
    public String getTitle() { return title; }
    public void setTitle(String v) { title = v; }
    public String getAuthor() { return author; }
    public void setAuthor(String v) { author = v; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String v) { isbn = v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { category = v; }
}
