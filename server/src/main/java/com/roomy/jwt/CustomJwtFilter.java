package com.roomy.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

    @Slf4j
public class CustomJwtFilter extends OncePerRequestFilter {

    private TokenProvider tokenProvider;

    public CustomJwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().equals("/auth/login")|| request.getServletPath().equals("auth/refresh")
             ){
            log.debug("login url : /auth/login  ");
        }else{
            String jwt = resolveToken(request);
            String requestURI = request.getRequestURI();
            if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
                Authentication auth = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else {
                log.info("token 정보 없음 uri : {}", requestURI);
            }
        }
        filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        log.info("resolveToken method  실행 : {}", request.getRequestURI());
        String bearerToken = request.getHeader(AUTHORIZATION);
        log.info("bearerToken : {}", bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }


}
