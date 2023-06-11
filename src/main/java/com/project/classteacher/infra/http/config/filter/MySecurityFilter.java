package com.project.classteacher.infra.http.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.domain.entity.DecodeToken;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.infra.http.dtos.ErrorDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class MySecurityFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(MySecurityFilter.class);

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws IOException {
        try {
            if (isValidHeader(request)) {
                Authentication auth = authentication(request.getHeader("Authorization"));
                SecurityContextHolder.getContext().setAuthentication(auth);
                request.setAttribute("user", auth.getPrincipal());
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.atError().log(e.getMessage());
            authenticationError(response);
        }
    }

    private Authentication authentication(String token) {
        DecodeToken userDecoded = Token.decode(token);
        return new UsernamePasswordAuthenticationToken(userDecoded, null, Collections.emptyList());
    }

    private void authenticationError(HttpServletResponse response) throws IOException {
        ErrorDTO error = new ErrorDTO(401, "User not authorized");
        response.setStatus(error.getStatus());
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(error));
        response.getWriter().flush();
    }

    private boolean isValidHeader(HttpServletRequest request) {
        return request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty();
    }

}
