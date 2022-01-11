package com.roomy.config.filter;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

// OncePerRequestFilter : a request passes through the filter only once in the filter chain,
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//http://localhost:8080/user/login  or http://localhost:8080/api/token/refresh
        if(request.getServletPath().equals("/user/login")||request.getServletPath().equals("/api/token/refresh")){
            //login
            filterChain.doFilter(request,response);
            // user would try to login and dont do anything
        }else{
            /**  /user/login 외의 url */
            //HttpHeaders 를 보면
            //	 The HTTP {@code Authorization} header field name.
            //	public static final String AUTHORIZATION = "Authorization";
            // authorizationHeader > header  에 token에대한 키가 있음
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            // token(access , refresh) > valid : 비밀번호 필요없음
            //send the request with the token ? "Bearer "+ token
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                /** header에 Autorization: "Bearer " 토큰이 있을때  */
                try {
                    String token = authorizationHeader.substring("Bearer ".length());// how may letters removed
                    // 나중에 refactoring 하기

}
