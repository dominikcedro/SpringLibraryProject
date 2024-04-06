package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.entity.User;
import com.example.SpringLibrary.exception.auth.UserNotExistingException;
import com.example.SpringLibrary.exception.loan.IdUserNotExistingException;
import com.example.SpringLibrary.exception.loan.LoanAlreadyExistingException;
import com.example.SpringLibrary.exception.loan.LoanBookNotExistingException;
import com.example.SpringLibrary.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserService userService;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserService userService) {
        this.loanRepository = loanRepository;
        this.userService = userService;
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
        Optional<Loan> existingLoan1 = loanRepository.findByBookBookIdAndUserId(loanDTO.getBookId(), loanDTO.getUserId());
        if(existingLoan1.isEmpty()){
            throw LoanBookNotExistingException.create(loanDTO.getBookId());
        }

        // check wheter user with said id exists
        Optional<User> existingUser = userService.getUserById(loanDTO.getUserId());
        if(existingUser.isEmpty()){
            throw IdUserNotExistingException.create(loanDTO.getUserId());
        }

        Loan loan = new Loan();
        User user = userService.getUserById(loanDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        loan.setUser(user);

        Book book = new Book();
        book.setBookId(loanDTO.getBookId());
        loan.setBook(book);

        loan.setLoan_date(loanDTO.getLoanDate());
        loan.setReturn_date(loanDTO.getReturnDate());
        return loanRepository.save(loan);
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
        loanRepository.deleteById(id);
    }
}