package com.example.catologodelivros.model;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final String category;
    private final String subject;
    private final String description;
    private final double price;
    private final int stock;
    private final int imageResId;

    public Book(int id, String title, String author, String category, String subject,
                String description, double price, int stock, int imageResId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.subject = subject;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageResId = imageResId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isAvailable() {
        return stock > 0;
    }
}
