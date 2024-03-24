package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.BookDetailsDTO;
import com.example.SpringLibrary.entity.BookDetails;
import com.example.SpringLibrary.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bookDetails")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping
    public Iterable<BookDetails> getAllBookDetails() {
        return bookDetailsService.getAllBookDetails();
    }

    @GetMapping("/{id}")
    public Optional<BookDetails> getBookDetailsById(@PathVariable Long id) {
        return bookDetailsService.getBookDetailsById(id);
    }

    @PostMapping
    public BookDetails addBookDetails(@RequestBody BookDetailsDTO bookDetailsDTO) {
        return bookDetailsService.saveBookDetails(bookDetailsDTO);
    }

    @PutMapping("/{id}")
    public BookDetails updateBookDetails(@PathVariable Long id, @RequestBody BookDetailsDTO bookDetailsDTO) {
        return bookDetailsService.updateBookDetails(id, bookDetailsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBookDetails(@PathVariable Long id) {
        bookDetailsService.deleteBookDetails(id);
    }
}