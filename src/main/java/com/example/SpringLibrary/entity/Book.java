package com.example.SpringLibrary.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookID;
    @Column
    private String ISBN;
    @Column
    private String Title;
    @Column
    private String Author;
    @Column
    private String Publisher;
    @Column
    private Integer YearPublished;
    @Column
    private Integer AvaibleCopies;

    public Long getBookID() {
        return BookID;
    }

    public void setBookID(Long bookID) {
        BookID = bookID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Integer getYearPublished() {
        return YearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        YearPublished = yearPublished;
    }

    public Integer getAvaibleCopies() {
        return AvaibleCopies;
    }

    public void setAvaibleCopies(Integer avaibleCopies) {
        AvaibleCopies = avaibleCopies;
    }
}
