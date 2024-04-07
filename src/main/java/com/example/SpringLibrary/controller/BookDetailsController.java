package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.BookDetailsDTO;
import com.example.SpringLibrary.entity.BookDetails;
import com.example.SpringLibrary.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')or hasRole('ROLE_READER')")
    public Iterable<BookDetails> getAllBookDetails() {
        return bookDetailsService.getAllBookDetails();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')or hasRole('ROLE_READER')")
    public Optional<BookDetails> getBookDetailsById(@PathVariable Long id) {
        return bookDetailsService.getBookDetailsById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public BookDetails addBookDetails(@RequestBody BookDetailsDTO bookDetailsDTO) {
        return bookDetailsService.saveBookDetails(bookDetailsDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public BookDetails updateBookDetails(@PathVariable Long id, @RequestBody BookDetailsDTO bookDetailsDTO) {
        return bookDetailsService.updateBookDetails(id, bookDetailsDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public void deleteBookDetails(@PathVariable Long id) {
        bookDetailsService.deleteBookDetails(id);
    }
}