package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.BookDTO;
import com.example.SpringLibrary.dto.book.AddBookResponseDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.Review;
import com.example.SpringLibrary.exception.book.BookAlreadyExistsException;
import com.example.SpringLibrary.exception.book.BookNotExistingException;
import com.example.SpringLibrary.exception.review.ReviewAlreadyExistingException;
import com.example.SpringLibrary.repository.BookRepository;
import com.example.SpringLibrary.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;


    @Autowired
    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }


    public AddBookResponseDTO addBook(BookDTO bookDTO) {

        Optional<Book> existingReview = bookRepository.findByisbn(bookDTO.getIsbn());
        if(existingReview.isPresent()){
            throw BookAlreadyExistsException.create(bookDTO.getIsbn());
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

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public void deleteBook(Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if(existingBook.isEmpty()){
            throw BookNotExistingException.create(id.toString());
        }
        reviewRepository.deleteByBookId(id);
        bookRepository.deleteById(id);
    }
    public Book updateBook(Long id, BookDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if(existingBook.isEmpty()){
            throw BookNotExistingException.create(id.toString());
        }

        Book book = existingBook.get();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setYearPublished(bookDTO.getYearPublished());
        book.setAvailableCopies(bookDTO.getAvaibleCopies());

        return bookRepository.save(book);
    }
}
// create deleteBook method here below

