package com.roomy.config.JWT;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


/**
 * 토큰의 생성과 토큰 유효성 검증하는 클래스
 * InitializingBean 을 implements 해서 afterPropertiesSet을 override한 이유??
 *  * 빈이 생성되고 주입을 받은 후에 secret 값(생성자에서 매개변수의 secret 값)을
 *  * Base64 Decode 해서 key 변수에 할당
 *  Interface to be implemented by beans that need to react
 *  once all their properties have been set by a BeanFactory:
 */
@Slf4j
@Component // bean 등록
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;
    
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-seconds}")long tokenValidityInMilliseconds) {
        // 빈이 생성되고 주입을 받은 후에 secret 값(생성자에서 매개변수의 secret 값)과 시간 넣어줌
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //Base64 Decode해서 key 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /** Authentication 객체의 권한정보를 이용해서 토큰을 생성하는 method*/
    public String createToken(Authentication authentication) {
        Long now = (new Date()).getTime();
    return Jwts.builder().setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, "USER")
            .signWith(key,SignatureAlgorithm.HS512)
            .setExpiration(new Date(now+this.tokenValidityInMilliseconds))
            .compact();

    }
    // Token 을 parameter로 받아 담겨있는 권한정보를
    // 이용해 authentication 객체를 리턴하는 메서드
    public Authentication getAuthentication(String jwt) {
        return null;
    }

    // 토큰을 받아  유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
