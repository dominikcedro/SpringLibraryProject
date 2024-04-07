package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    // find by bookId if book exists
    Optional<Book> findByBookId(Long bookId);

    Optional<Book> findByisbn(String isbn);
}