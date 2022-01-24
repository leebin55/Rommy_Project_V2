package com.roomy.config;


import com.roomy.config.JWT.JwtAccessDeniedHandler;
import com.roomy.config.JWT.JwtEntryPoint;
import com.roomy.config.JWT.TokenProvider;
import com.roomy.config.JWT.CustomJwtFilter;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter; // 다른 패키지로 import 하면 빈인식못함
// 스프링은 타입으로 빈을 찾기 때문에


//@Configuration : @EnableWebSecurity  에 포함되어있음
 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true)// Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우
 // @PreAuthorize 어노테이션을 사용하는데, 해당 어노테이션에 대한 설정을 활성화시키는 어노테이션이다
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * SecurityContextPersistenceFilter는 이후의 필터동작들이 끝난 후,
     * 다시 자신의 실행흐름으로 돌아와,
     * 인증 완료된 Authentication 객체가 존재할 경우,
     * 이를 SecurityContextRepository에 저장.(기본 구현은 in memory)
     */

    private final TokenProvider tokenProvider;
    private final JwtEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;



    public SpringSecurityConfig(TokenProvider tokenProvider,
                                JwtEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler,
                                CorsFilter corsFilter) {

        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;

        this.corsFilter = corsFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/auth/login").permitAll()
                .antMatchers("/users/sign_up").permitAll()
                .antMatchers("/feeds").permitAll()
                .anyRequest() // 나머지 요청들에 대해서는
                .authenticated();// 인증받아야 한다.;


        http
                .csrf().disable() //token 을 사용하는 방식이기 때문에 csrf를 disable
                // corsFilter 와 에러핸들링을 직접 만든 클래스로 바꾸기
                .addFilterBefore(corsFilter,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//필터등 에러 커스터마이징
     // spring security  가 제공하는 클래스 말고 직접 만든 클래스로 변경하겟다
        .and().
                addFilterBefore(new CustomJwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
        //------------------------ cors config > CorsConfig 로 분리
//        http.cors().configurationSource(request -> {
//            CorsConfiguration cors = new CorsConfiguration();
//            cors.setAllowedOrigins(List.of("http://localhost:3000"));
//            cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
//            cors.setAllowCredentials(true);
//            cors.setAllowedHeaders(List.of("*"));
//            return cors;
//        });
    }

//---------------------빈등록------------------------------------------------


    @Bean
    public PasswordEncoder passwordEncoder(){
        // 암호화 하는 알고리즘은 여b러가지 존재 (SHA256,scrypt,pbkdf2...)
        // 기본 해시 함수인 SHA, MD5등을 이용하는 것은 빠른 처리속도로 인하여 보안에 취약.
        //해시를 강하게 하기 위해서는 무작위 salt와 함께 반복적인 해싱작업이 필요하다.
        //구현이 쉽고 비교적 위의 알고릴즘보다 강력한 BCrypt 를 이용
        // 만약에 여러 이미 다른 알고리즘으로 암호화를 저장되어있지만 더 고도화된 암호화를 하고 싶다면
        // DelegatingPasswordEncoder 를 이용하자
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
