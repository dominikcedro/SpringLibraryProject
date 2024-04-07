package com.example.SpringLibrary.controller;

import com.example.SpringLibrary.dto.auth.LoginDTO;
import com.example.SpringLibrary.dto.auth.LoginResponseDTO;
import com.example.SpringLibrary.dto.auth.RegisterDTO;
import com.example.SpringLibrary.dto.auth.RegisterResponseDTO;
import com.example.SpringLibrary.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController is a REST controller class in the Spring Framework, responsible for handling authentication-related HTTP requests.
 * It is mapped to the /api/auth endpoint.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * The constructor autowires the AuthService dependency.
     * @param authService A service class that contains business logic related to authentication.
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     * Endpoint: /api/auth/register
     * HTTP Method: POST
     * Access: ROLE_ADMIN, ROLE_MOD
     * @param requestBody A RegisterDTO object containing the registration details.
     * @return A ResponseEntity containing a RegisterResponseDTO object and HTTP status code 201 (Created). 
     * The RegisterResponseDTO object contains the response details of the registration.
     */
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid registration details supplied",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content)
    })
    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody){
        RegisterResponseDTO dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /**
     * Logs in a user.
     * Endpoint: /api/auth/login
     * HTTP Method: POST
     * Access: All roles
     * @param requestBody A LoginDTO object containing the login details.
     * @return A ResponseEntity containing a LoginResponseDTO object and HTTP status code 201 (Created). 
     * The LoginResponseDTO object contains the response details of the login.
     */
    @Operation(summary = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User logged in successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid login details supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)
    })
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO requestBody){
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}