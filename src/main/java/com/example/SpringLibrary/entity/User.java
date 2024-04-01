package com.example.SpringLibrary.entity;

import com.example.SpringLibrary.Role;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "user", schema = "springlib")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",unique = true, nullable = false)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Auth auth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "loan")
    private List<Loan> loan;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "review")
    private List<Review> review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

}

