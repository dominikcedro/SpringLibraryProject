package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public Iterable<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public Loan addLoan(@RequestBody LoanDTO loanDTO) {
        return loanService.saveLoan(loanDTO);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        return loanService.updateLoan(id, loanDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }
}