package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.entity.Book;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.entity.User;
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
        Loan loan = new Loan();
        User user = userService.getUserById(loanDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        loan.setUser(user);

        Book book = new Book();
        book.setBookID(loanDTO.getBookId());
        loan.setBook(book);

        loan.setLoanDate(loanDTO.getLoanDate());
        loan.setReturnDate(loanDTO.getReturnDate());
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        User user = userService.getUserById(loanDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        loan.setUser(user);

        Book book = new Book();
        book.setBookID(loanDTO.getBookId());
        loan.setBook(book);

        loan.setLoanDate(loanDTO.getLoanDate());
        loan.setReturnDate(loanDTO.getReturnDate());
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}