package com.jo0oy.api.security.jwt;

import com.jo0oy.api.global.exception.ApiException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        @NotNull HttpServletRequest request,
        @NotNull HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        log.info("JwtExceptionFilter 진입");

        try {
            filterChain.doFilter(request, response);
        } catch (ApiException ex) {
            CustomErrorSend.handleException(response, ex.getErrorCodeIfs());
        }
    }
}
