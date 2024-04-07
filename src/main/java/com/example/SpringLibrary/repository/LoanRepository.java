package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Optional<Loan> findByBookBookIdAndUserId(Long bookId, Long userId);
}