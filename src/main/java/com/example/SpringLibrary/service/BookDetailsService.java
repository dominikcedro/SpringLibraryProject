package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.BookDetailsDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.BookDetails;
import com.example.SpringLibrary.exception.book.BookNotExistingException;
import com.example.SpringLibrary.repository.BookDetailsRepository;
import com.example.SpringLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<BookDetails> getAllBookDetails() {
        return bookDetailsRepository.findAll();
    }

    public Optional<BookDetails> getBookDetailsById(Long id) {
        return bookDetailsRepository.findById(id);
    }

    public BookDetails saveBookDetails(BookDetailsDTO bookDetailsDTO) {
        // get Book entity to input to BookDetails
        Book book = bookRepository.findById(bookDetailsDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));


        BookDetails bookDetails = new BookDetails();
        bookDetails.setBook(book); // set the bookId
        bookDetails.setAuthor_summary(bookDetailsDTO.getAuthor_summary());
        bookDetails.setOrigin_country(bookDetailsDTO.getOrigin_country());
        bookDetails.setCategory(bookDetailsDTO.getCategory());
        bookDetails.setSummary(bookDetailsDTO.getSummary());
        return bookDetailsRepository.save(bookDetails);
    }

    public BookDetails updateBookDetails(Long id, BookDetailsDTO bookDetailsDTO) {
        BookDetails bookDetails = bookDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("BookDetails not found"));
        bookDetails.setAuthor_summary(bookDetailsDTO.getAuthor_summary());
        bookDetails.setOrigin_country(bookDetailsDTO.getOrigin_country());
        bookDetails.setCategory(bookDetailsDTO.getCategory());
        bookDetails.setSummary(bookDetailsDTO.getSummary());
        return bookDetailsRepository.save(bookDetails);
    }

    public void deleteBookDetails(Long id) {
        bookDetailsRepository.deleteById(id);
    }
}