package com.example.SpringLibrary.dto;

public class BookDTO {
    private Long id;

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer yearPublished;
    private Integer avaibleCopies;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getAvaibleCopies() {
        return avaibleCopies;
    }

    public void setAvaibleCopies(Integer avaibleCopies) {
        this.avaibleCopies = avaibleCopies;
    }
}