package com.roomy.config.filter;

import com.roomy.jwt.TokenProvider;
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
 * 같이 만들어서 UsernamePasswordAuthenticationFilter이전에 등록
 */
// GenericFilterBean 에서 Once....  로 변경 >사용자의 한번의 요청 당 딱 한번만실행되는 필터
public class JwtFilter extends OncePerRequestFilter {


    private TokenProvider tokenProvider;

    //TokenProvider 를 주입받음
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    /**
     * Request 로 들어오는 Jwt Token 의 유효성을 검증하는 filter  를 filterChain에 등록
     *   실제 필터링 로직
     *    토큰의 인증정보를 SecurityContext에 저장하는 역할 수행*/
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    HttpServletRequest httpRequest = request;
        //resolveToken를 통해 httpServletRequest에서 토큰을 받아
        String jwt = resolveToken(httpRequest);
    }

    private String resolveToken(HttpServletRequest request) {

        // http request  header에서 Authorization을 가지고 옴
        String bearerToken = request.getHeader(AUTHORIZATION);
        // "Bearer " 꼭 띄어쓰기 하기
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);// 7번째 이후 문자열 부터 (Bearer )띄어쓰기 다음부터
        }
    }


}
