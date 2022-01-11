package com.roomy.config.filter;

import com.roomy.config.JWT.TokenProvider;
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

/**
 * JWT 토큰을 위한 필터
 * 그래서 JwtFilter를 다음과
 * 같이 만들어서 UsernamePasswordAuthenticationFilter 이전에 등록
 */
// GenericFilterBean 에서 Once....  로 변경 >사용자의 한번의 요청 당 딱 한번만실행되는 필터
    @Slf4j
public class CustomJwtFilter extends OncePerRequestFilter {


    private TokenProvider tokenProvider;

    //TokenProvider 를 주입받음
    public CustomJwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    /**
     * Request 로 들어오는 Jwt Token 의 유효성을 검증하는 filter  를 filterChain에 등록
     *   실제 필터링 로직
     *    토큰의 인증정보를 SecurityContext에 저장하는 역할 수행*/
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().equals("/user/login")||
                request.getServletPath().equals("/token/refresh")){
            // user 가 login 하게설정
            log.debug("/user/login ");

            filterChain.doFilter(request,response);
        }else{
            // 위의 url  이외일때
            //resolveToken를 통해 httpServletRequest의 Header  에서 jwt 가져오기
            String jwt = resolveToken(request);
            String requestURI = request.getRequestURI();
            // token 유효성 검증
            if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
                // TokenProvider 에서 validateToken method 통과하면
                // > authentication 받아오기
                Authentication auth = tokenProvider.getAuthentication(jwt);
                // security context 에 set
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("Security Context 에 {} 저장 , uri : {}",auth.getName(),requestURI );
            }else {
                log.info("token 정보 없음 uri : {}", requestURI);
            }
        }



        filterChain.doFilter(request,response);
    }

    /**
     * client 에서 요청 할때 Header 에 "Authorization" : "Bearer ***" 토큰을 보냄
     * Request Header 에서 토큰 정보를 가져오기 위한 메서드
     *  */
    private String resolveToken(HttpServletRequest request) {

        // http request  header에서 Authorization을 가지고 옴
        String bearerToken = request.getHeader(AUTHORIZATION);
        // "Bearer " 꼭 띄어쓰기 하기
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);// 7번째 이후 문자열 부터 (Bearer )띄어쓰기 다음부터
        }
        return null;
    }


}
