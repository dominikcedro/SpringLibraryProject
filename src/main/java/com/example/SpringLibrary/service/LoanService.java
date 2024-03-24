package com.example.SpringLibrary.service;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Iterable<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan saveLoan(LoanDTO loanDTO) {
        Loan loan = new Loan();
        loan.setUserId(loanDTO.getUserId());
        loan.setBookId(loanDTO.getBookId());
        loan.setLoanDate(loanDTO.getLoanDate());
        loan.setReturnDate(loanDTO.getReturnDate());
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setUserId(loanDTO.getUserId());
        loan.setBookId(loanDTO.getBookId());
        loan.setLoanDate(loanDTO.getLoanDate());
        loan.setReturnDate(loanDTO.getReturnDate());
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}