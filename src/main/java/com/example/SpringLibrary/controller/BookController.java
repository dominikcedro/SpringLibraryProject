package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.dto.book.AddBookResponseDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.exception.book.BookNotExistingException;
import com.example.SpringLibrary.repository.BookRepository;
import com.example.SpringLibrary.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
// for deleting reviews
import com.example.SpringLibrary.repository.ReviewRepository;


import java.util.Optional;

@RestController
@RestControllerAdvice
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')or hasRole('ROLE_READER')")
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')or hasRole('ROLE_READER')")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> BookNotExistingException.create(id.toString()));
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public ResponseEntity<AddBookResponseDTO> addBook(@RequestBody BookDTO bookDTO) {
        AddBookResponseDTO response = bookService.addBook(bookDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public void deleteBook(@PathVariable Long id)
    {    reviewRepository.deleteByBookId(id);

        bookRepository.deleteById(id);
    }
}