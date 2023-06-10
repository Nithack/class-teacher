package com.project.classteacher.infra.http.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.entity.User;
import com.project.classteacher.infra.http.dtos.ErrorDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class MySecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()) {
            Authentication auth = authentication(request.getHeader("Authorization"));
            if (auth == null) {
                authenticationError(response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("Without authorization");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        System.out.println("With authorization");
        filterChain.doFilter(request, response);
    }

    private Authentication authentication(String token) {
        User auth = Token.decode(token);
        return new UsernamePasswordAuthenticationToken(auth, null, Collections.emptyList());
    }

    private void authenticationError(HttpServletResponse response) throws IOException {
        ErrorDTO error = new ErrorDTO(401, "User not authorized");
        response.setStatus(error.getStatus());
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(error));
        response.getWriter().flush();
    }

}
