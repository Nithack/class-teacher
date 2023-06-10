package com.project.classteacher.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MySecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader("Authorization") == null) {
            System.out.println("Sem autorização");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        System.out.println(request.getHeader("Authorization"));
        filterChain.doFilter(request, response);
    }
}
