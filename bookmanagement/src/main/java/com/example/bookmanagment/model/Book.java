package com.example.bookmanagment.model;

public class Book {
    private int id;
    private String title;
    private String Author;
    private double price;

    public Book(Integer id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        Author = author;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
