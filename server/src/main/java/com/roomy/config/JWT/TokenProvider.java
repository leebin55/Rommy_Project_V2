package com.roomy.config.JWT;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;


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
    private final long TOKEN_EXPIRE_TIME;


    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-seconds}")long tokenValidityInMilliseconds) {
        // 빈이 생성되고 주입을 받은 후에 secret 값(생성자에서 매개변수의 secret 값)과 시간 넣어줌
        this.secret = secret;
        this.TOKEN_EXPIRE_TIME = tokenValidityInMilliseconds;
    }

    /**
     * HMAC-SHA 서명 알고리즘 HS256, HS384, HS512는 비밀키가 최소한 알고리즘의
     * 서명 길이만큼의 비트를 가지고 있어야함을 요구한다.
     * HS256 은 HMAC-SHA-256이고, 256bits(32bytes)인 digest를 생성한다,
     * 따라서 HS256은 사용자가 비밀키로 최소한 32bytes 길이를 사용하도록 요구한다.
     * HS384 은 HMAC-SHA-384이고, 384bits(48bytes)인 digest를 생성한다,
     * 따라서 HS384은 사용자가 비밀키로 최소한 48bytes 길이를 사용하도록 요구한다.
     * HS512 은 HMAC-SHA-512이고, 512bits(64bytes)인 digest를 생성한다,
     * 따라서 HS512은 사용자가 비밀키로 최소한 64bytes 길이를 사용하도록 요구한다.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //Base64 Decode해서 key 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        log.info("key format:{} algorithm:{} String: {}",
                key.getFormat(),key.getAlgorithm(),key.toString());
    }

    /** Authentication 객체의 권한정보를 이용해서 토큰을 생성하는 method*/
    public String createToken(Authentication authentication) {
        // role 을 여러개 가질 수 있지만 현재 프로젝트에서는 한가만 가짐
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String access_token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(currentTimeMillis()+TOKEN_EXPIRE_TIME))
                .compact();// String 형태로 compact > 압축

        // 우선 access_token 만 발급 나중에 변경예정
//         String refresh_token = Jwts.builder()
//                .setSubject(authentication.getName())
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(new Date(currentTimeMillis()+TOKEN_EXPIRE_TIME*6))
//                .compact();// String 형태로 compact > 압축
//        Map<String , String> tokens = new HashMap<>();
//        tokens.put("access_token",access_token);
//        tokens.put("refresh_token",refresh_token);
        return access_token;
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
