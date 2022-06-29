package com.roomy.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.currentTimeMillis;
import static java.util.stream.Collectors.toList;


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
        this.secret = secret;
        this.TOKEN_EXPIRE_TIME = tokenValidityInMilliseconds;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        log.info("key format:{} algorithm:{} String: {}",
                key.getFormat(),key.getAlgorithm(),key.toString());
    }

    public String  createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return createAccessToken(authentication.getName(),authorities);

    }

    private String createAccessToken(String name,String authority){
        return Jwts.builder()
                .setSubject(name)
                .claim(AUTHORITIES_KEY, authority)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(new Date(currentTimeMillis()+TOKEN_EXPIRE_TIME))
                .compact();
    }

//    private String createRefreshToken(String name){
//        return  Jwts.builder()
//                .setSubject(name)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(new Date(currentTimeMillis()+TOKEN_EXPIRE_TIME*6))
//                .compact();
//    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Collection<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(toList());

        User principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal,token,authorities );
    }

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
