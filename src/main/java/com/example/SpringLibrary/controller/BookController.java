package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.repository.BookRepository;
import com.example.SpringLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}