package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}