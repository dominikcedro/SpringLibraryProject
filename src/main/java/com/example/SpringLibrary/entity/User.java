package com.example.SpringLibrary.entity;

import com.example.SpringLibrary.Role;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long UserID;
    @Column

    private String Username;
    @Column

    private String Password;
    @Column

    @Enumerated(EnumType.STRING)
    private Role Role;
    @Column

    private String Email;
    @Column

    private String Name;

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role role) {
        Role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
