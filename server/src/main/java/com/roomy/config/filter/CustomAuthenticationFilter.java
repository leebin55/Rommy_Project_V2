package com.roomy.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
// Authentication 관련(로그인)
//UsernamePasswordAuthenticationFilter 는 AbstractAuth....Filter 상속받고 그 클래스는 또 GenericFilterBean 을 상속받음
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 의존성 주입
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        /** username과 password를 얻어오고 그 값으로
         * UsernamePasswordAuthenticationToken(Authentication)을 생성
         *  UsernamePasswordAuthenticationToken 은
         * Authentication 인터페이스의 구현체*/

        String username = request.getParameter("username");
        String password = request.getParameter("password");
       log.info("username :{}, pw : {}", username, password);
       // 인증 토큰 생성하고 authenticationManager 에게 넘겨줌
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        // authenticationManager 호출 > authentication
        // 인증이 성공하면? successfulAuthentication()  실행
        //AuthenticationManager(구현체인 ProviderManager)에게 인증을 진행하도록 위임
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {


    }

    // 로그인 실패할때 > 로그인 실패 횟수 초과 등 실패시 추가 작업
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
//    }
}
