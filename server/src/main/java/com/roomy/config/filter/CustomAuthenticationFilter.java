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

/**
 * UserAuthFilter는 먼저, request로부터 username, password를
 * 추출하여 인증이 완료되지 않은 UsernamePasswordAuthenticationToken(Authentication 인터페이스의 구현체)을 만든 후
 * . 이 token을 ProviderManager(AuthenticationManager의 구현체)에게 넘겨 인증 책임을
 * 위임합니다.
 */
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

    /**
     * ProviderManager는 자신이 가진 Provider들 중에서, Parameter로 들어온
     * Authentication을 인증할 수 있는 Provider를 찾은 후, 인증 책임을 위임
     *  Provider중 UsernamePasswordAuthenticationToken을 처리할 수 있는 Provider는
     *  => DaoAuthenticationProvider
     *  token안에는 사용자가 전달한 username과 password가 들어있다.
     *  이 중 username을 사용하여 현재 저장되어 있는 user(Account)를
     *  UserDetailsService에 요청한다.
     *  유저를 받아온 후 password일치 여부등, 유저를 검증하여 성공했다면
     *  Authentication 객체를 반환하고 실패했다면 예외를 발생
     *  . Provider가 요청한 user를 받아오기 위해 loadByUsername 메서드가 호출됩니다.
     */

    //다음 filter를 실행할 수 있도록 chain.doFilter를 호출
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
