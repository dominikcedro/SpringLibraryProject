package com.example.SpringLibrary.repository;

import com.example.SpringLibrary.entity.BookDetails;
import org.springframework.data.repository.CrudRepository;

public interface BookDetailsRepository extends CrudRepository<BookDetails, Long> {
}