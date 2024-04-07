package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Iterable<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public Loan addLoan(@RequestBody LoanDTO loanDTO) {
        return loanService.saveLoan(loanDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public Loan updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        return loanService.updateLoan(id, loanDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }
}