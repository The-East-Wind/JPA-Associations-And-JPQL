package com.cg.jpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="author_master")
public class Author {
    @Id
    @Column(name="author_id")
    @GeneratedValue(generator = "author_id_gen",strategy = GenerationType.AUTO)
    private int authorId;
    @Column(name="author_name")
    private String authorName;
    @OneToMany
    private Set<Book> books = new HashSet<>();
    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
