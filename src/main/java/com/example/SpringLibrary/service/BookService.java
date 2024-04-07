package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.dto.book.AddBookResponseDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.Review;
import com.example.SpringLibrary.exception.review.ReviewAlreadyExistingException;
import com.example.SpringLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public AddBookResponseDTO addBook(BookDTO bookDTO) {

        Optional<Book> existingReview = bookRepository.findByBookId(bookDTO.getBookId());
        if(existingReview.isPresent()){
            throw ReviewAlreadyExistingException.create(reviewDTO.getBookId(), reviewDTO.getUserId());
        }
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setYearPublished(bookDTO.getYearPublished());
        book.setAvailableCopies(bookDTO.getAvaibleCopies());
        Book createdBook = bookRepository.save(book);
        return new AddBookResponseDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher());
    }
}