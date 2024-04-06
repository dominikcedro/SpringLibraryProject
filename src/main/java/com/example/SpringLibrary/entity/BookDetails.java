package com.example.SpringLibrary.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "book_details", schema = "springlib")
public class BookDetails {
    @Id
    @GeneratedValue
    private Long book_details_id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private String origin_country;

    @Column
    private String category;

    @Column
    private String author_summary;

    @Column
    private String summary;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book_id) {
        this.book = book_id;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_summary() {
        return author_summary;
    }

    public void setAuthor_summary(String author_summary) {
        this.author_summary = author_summary;
    }

    public Long getBook_details_id() {
        return book_details_id;
    }

    public void setBook_details_id(Long book_details_id) {
        this.book_details_id = book_details_id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}