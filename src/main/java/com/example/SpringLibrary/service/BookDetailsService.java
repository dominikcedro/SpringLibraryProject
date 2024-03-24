package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.BookDetailsDTO;
import com.example.SpringLibrary.entity.BookDetails;
import com.example.SpringLibrary.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public Iterable<BookDetails> getAllBookDetails() {
        return bookDetailsRepository.findAll();
    }

    public Optional<BookDetails> getBookDetailsById(Long id) {
        return bookDetailsRepository.findById(id);
    }

    public BookDetails saveBookDetails(BookDetailsDTO bookDetailsDTO) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookId(bookDetailsDTO.getBookId());
        bookDetails.setAuthor(bookDetailsDTO.getAuthor());
        bookDetails.setPublicationDate(bookDetailsDTO.getPublicationDate());
        bookDetails.setSummary(bookDetailsDTO.getSummary());
        return bookDetailsRepository.save(bookDetails);
    }

    public BookDetails updateBookDetails(Long id, BookDetailsDTO bookDetailsDTO) {
        BookDetails bookDetails = bookDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("BookDetails not found"));
        bookDetails.setBookId(bookDetailsDTO.getBookId());
        bookDetails.setAuthor(bookDetailsDTO.getAuthor());
        bookDetails.setPublicationDate(bookDetailsDTO.getPublicationDate());
        bookDetails.setSummary(bookDetailsDTO.getSummary());
        return bookDetailsRepository.save(bookDetails);
    }

    public void deleteBookDetails(Long id) {
        bookDetailsRepository.deleteById(id);
    }
}