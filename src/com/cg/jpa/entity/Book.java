package com.cg.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    private String isbn;
    private String title;
    private float price;
    public Book() {
    }

    public Book(String isbn, String title, float price) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
