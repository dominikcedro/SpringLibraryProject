package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.LoanDTO;
import com.example.SpringLibrary.entity.Loan;
import com.example.SpringLibrary.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Get all loans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all loans",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Loan.class))) }),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content)
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Iterable<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @Operation(summary = "Get a loan by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the loan",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Loan.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB') or hasRole('ROLE_READER')")
    public Optional<Loan> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @Operation(summary = "Add a new loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Loan.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid loan details supplied",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public Loan addLoan(@RequestBody LoanDTO loanDTO) {
        return loanService.saveLoan(loanDTO);
    }

    @Operation(summary = "Update a loan by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Loan.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid loan details supplied",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public Loan updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        return loanService.updateLoan(id, loanDTO);
    }

    @Operation(summary = "Delete a loan by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Loan deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')or hasRole('ROLE_LIB')")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }
}