package com.example.SpringLibrary.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_details", schema = "springlib")
public class BookDetails {
    @Id
    @GeneratedValue
    private Long book_details_id;

    @Column
    private Long book_id;

    @Column
    private String author;

    @Column
    private String publication_date;

    @Column
    private String summary;

    public Long getBook_details_id() {
        return book_details_id;
    }

    public void setBook_details_id(Long book_details_id) {
        this.book_details_id = book_details_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}