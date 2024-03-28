package com.example.SpringLibrary.test;

import com.example.SpringLibrary.security.JWTTokenFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class JWTTokenFilterTest {

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JWTTokenFilter jwtTokenFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilter() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJyb2xlIjoiUk9MRV9BRE1JTiIsImlhdCI6MTcxMTUzMDg5MiwiZXhwIjoxNzExNTM0NDkyfQ.j47rT6Z77Mmz-FxYenTihzwKpHn66h8eYbG57aO6Ux4");

        UserDetails userDetails = new User("admin", "admin123", new ArrayList<>());
        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);

        jwtTokenFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertTrue(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken);
    }
}