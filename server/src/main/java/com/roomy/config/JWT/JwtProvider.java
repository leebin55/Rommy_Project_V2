package com.roomy.config.JWT;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * 토큰의 생성과 토큰의 유효성 검증을 하는 클래스
 * InitializingBean 을 implements해서 afterPropertiesSet을 override한 이유는
 * 빈이 생성되고 주입을 받은 후에 secret 값(생성자에서 매개변수의 secret 값)을
 * Base64 Decode 해서 key 변수에 할당
 */
@Slf4j
@Component // 빈으로 등록
public class JwtProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;

    // jwt.yml 파일에서 값 가져오기
    // InitializingBean >빈이 생성되고 주입을 받은 후에 secret 값(생성자에서 매개변수의 secret 값)을
    public JwtProvider(@Value("${jwt.secret}") String secret,
    @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds)  {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //Base64 Decode해서 key 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /** Authentication 객체의 권한정보를 이용해서 토큰을 생성하는 method*/
    public String createToken(Authentication authentication){
        String authority =
    }
}
