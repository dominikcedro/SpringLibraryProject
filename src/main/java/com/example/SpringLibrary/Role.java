package com.example.SpringLibrary;

public enum Role {
    ROLE_ADMIN, // admin of the system, number of people with this role should be limited
    ROLE_MOD, // moderator - manager of library branch, allowed to modify user data, reviews, loans, loanLimits
    ROLE_READER, // average reader
    ROLE_LIB, // librarian of the library branch, allowed to add books to the library
}