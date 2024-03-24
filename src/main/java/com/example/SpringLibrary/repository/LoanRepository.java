package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
}