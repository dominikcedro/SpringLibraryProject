package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.dto.UserDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.exception.auth.UserNotExistingException;
import com.example.SpringLibrary.exception.loan.*;
import com.example.SpringLibrary.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringLibrary.repository.BookRepository;

import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserService userService;
    private final BookRepository bookRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository, UserService userService, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    public Iterable<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan saveLoan(LoanDTO loanDTO) {
        // check if loan for this book id and user id already exists if so throw LoanAlreadyExistingException
        Optional<Loan> existingLoan = loanRepository.findByBookBookIdAndUserId(loanDTO.getBookId(), loanDTO.getUserId());
        if(existingLoan.isPresent()){
            throw LoanAlreadyExistingException.create(loanDTO.getBookId(), loanDTO.getUserId());
        }
        // check if book for this loan with book id exists, if not throw exception
        Optional<Book> existingLoan1 = bookRepository.findByBookId(loanDTO.getBookId());
        if(existingLoan1.isEmpty()){
            throw LoanBookNotExistingException.create(loanDTO.getBookId());
        }

        // check whether user with said id exists
        Optional<User> existingUser = userService.getUserById(loanDTO.getUserId());
        if(existingUser.isEmpty()){
            throw IdUserNotExistingException.create(loanDTO.getUserId());
        }

        // check if user has more than 5 loans (limit)
        User user = userService.getUserById(loanDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getLoanCount() >= 5){
            throw LoanLimitExceededException.create(loanDTO.getUserId());
        }

        Loan loan = new Loan();
        Book book = bookRepository.findById(loanDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));

        if(book.getAvailableCopies() <= 0){
            throw NoBooksAvailableException.create(book.getTitle());

        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        loan.setUser(user);
        loan.setBook(book);
        loan.setLoan_date(loanDTO.getLoanDate());
        loan.setReturn_date(loanDTO.getReturnDate());

        Loan savedLoan = loanRepository.save(loan);

        user.setLoanCount(user.getLoanCount() + 1);
        UserDTO userDTO = userService.convertToUserDTO(user);
        userService.updateUser(user.getId(), userDTO);

        return savedLoan;
    }

    public Loan updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        User user = userService.getUserById(loanDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        loan.setUser(user);

        Book book = new Book();
        book.setBookId(loanDTO.getBookId());
        loan.setBook(book);

        loan.setLoan_date(loanDTO.getLoanDate());
        loan.setReturn_date(loanDTO.getReturnDate());
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        User user = loan.getUser();

        // decrement user's loanCount and save user
        user.setLoanCount(user.getLoanCount() - 1);
        UserDTO userDTO = userService.convertToUserDTO(user);
        userService.saveUser(userDTO);

        loanRepository.deleteById(id);
    }


}